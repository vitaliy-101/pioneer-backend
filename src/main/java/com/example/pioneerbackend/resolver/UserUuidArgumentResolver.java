package com.example.pioneerbackend.resolver;

import com.example.pioneerbackend.annotations.UserUuidParam;
import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.util.UserUuid;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserUuidArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserUuid.class) &&
                parameter.hasParameterAnnotation(UserUuidParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            userId = user.getId();
        }
        var uuid = webRequest.getHeader("Guest-UUID");
        return new UserUuid(userId, uuid);
    }
}