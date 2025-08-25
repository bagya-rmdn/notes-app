package com.example.notes_app.security.impl;

import com.example.notes_app.entity.UserEntity;
import com.example.notes_app.exception.ServiceException;
import com.example.notes_app.repository.UserRepository;
import com.example.notes_app.security.AuthenticationContext;
import com.example.notes_app.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationContextImpl implements AuthenticationContext {
    private final UserRepository userRepository;
    @Override
    public UserEntity getCurrentLoggedInUser() {
        Authentication authentication = this.getAuthentication();

        if (authentication.getPrincipal().equals("anonymousUser") || !authentication.isAuthenticated()) {
            return null;
        } else {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            UserEntity user = securityUser.getUser();
            user = userRepository.findById(user.getId())
                    .orElseThrow(() -> new ServiceException("User not found", ServiceException.FORBIDDEN_STATUS_CODE));
            return user;
        }

    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
