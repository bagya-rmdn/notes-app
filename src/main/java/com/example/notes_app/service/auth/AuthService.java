package com.example.notes_app.service.auth;

import com.example.notes_app.model.request.UserRequest;
import com.example.notes_app.model.response.UserResponse;

public interface AuthService {
    UserResponse signUp(UserRequest request);

    UserResponse SignIn(UserRequest request);
}
