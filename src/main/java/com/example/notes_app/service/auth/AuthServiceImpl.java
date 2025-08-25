package com.example.notes_app.service.auth;

import com.example.notes_app.entity.UserEntity;
import com.example.notes_app.exception.ServiceException;
import com.example.notes_app.model.request.UserRequest;
import com.example.notes_app.model.response.UserResponse;
import com.example.notes_app.repository.UserRepository;
import com.example.notes_app.util.JwtUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public UserResponse signUp(UserRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new ServiceException("Username is required", ServiceException.BAD_REQUEST_STATUS_CODE);
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ServiceException("Username already exists", ServiceException.FORBIDDEN_STATUS_CODE);
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        String token = jwtUtils.generateToken(user);

        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();
    }

    @Override
    public UserResponse SignIn(UserRequest request) {
        Optional<UserEntity> userOptional = userRepository.getByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            throw new ServiceException("Username not found: " + request.getUsername(), ServiceException.NOT_FOUND_STATUS_CODE);
        }

        UserEntity user = userOptional.get();

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new ServiceException("Invalid password", ServiceException.UNAUTHORIZED_STATUS_CODE);
        }

        String token = jwtUtils.generateToken(user);

        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();
    }

}
