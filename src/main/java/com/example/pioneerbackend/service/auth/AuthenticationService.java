package com.example.pioneerbackend.service.auth;

import com.example.pioneerbackend.dto.auth.AuthResponse;
import com.example.pioneerbackend.entity.auth.Role;
import com.example.pioneerbackend.entity.auth.Token;
import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.exceptions.ExistByEmailException;
import com.example.pioneerbackend.exceptions.LoginException;
import com.example.pioneerbackend.exceptions.NotFoundByEmailException;
import com.example.pioneerbackend.repository.TokenRepository;
import com.example.pioneerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.example.pioneerbackend.util.JwtUtils.encodePassword;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    private void revokeAllTokenByUseId(User user) {
        var validTokenListByUserId = tokenRepository.findAllAccessTokensByUserId(user.getId());
        if (!validTokenListByUserId.isEmpty()) {
            validTokenListByUserId.forEach(token -> token.setLoggedOut(true));
        }
        tokenRepository.saveAll(validTokenListByUserId);
    }

    private void saveUserToken(String jwt, User user) {
        var token = new Token();
        token.setAccessToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public AuthResponse authenticate(User user) {
        if (existByEmail(user.getEmail())) {
            throw new ExistByEmailException(User.class, user.getEmail());
        }
        user.setRole(Role.USER);
        user.setPassword(encodePassword(user.getPassword()));
        user = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        saveUserToken(jwt, user);
        return new AuthResponse(jwt);
    }

    public AuthResponse authorize(User request) throws LoginException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new LoginException(User.class);
        }
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundByEmailException(User.class, request.getEmail()));
        var jwt = jwtService.generateToken(user);
        revokeAllTokenByUseId(user);
        saveUserToken(jwt, user);
        return new AuthResponse(jwt);
    }

    public Boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
