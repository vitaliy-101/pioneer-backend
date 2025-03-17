package com.example.pioneerbackend.service.user;

import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundByIdException(User.class, id));
    }
}
