package com.eteacher.service.security;

import com.eteacher.service.entity.User;
import com.eteacher.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class    ActiveUserContext {

    private final UserRepository userRepository;

    public String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        String userId = (String) jwt.getClaims().get("user_id");
        return userId;
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        User user = userRepository.findByUserName((String) jwt.getClaims().get("preferred_username"));
        return user;
    }

    public String getLoggedInUserToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();

        return jwt.getTokenValue();
    }
}
