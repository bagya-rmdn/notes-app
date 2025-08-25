package com.example.notes_app.security;

import com.example.notes_app.entity.UserEntity;

public interface AuthenticationContext {
    UserEntity getCurrentLoggedInUser();
}
