package com.example.notes_app.controller;

import com.example.notes_app.model.request.UserRequest;
import com.example.notes_app.model.response.UserResponse;
import com.example.notes_app.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Sign Up New User", operationId = "signUpNewUser", description = "Sign Up New User")
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @Operation(summary = "Sign In User", operationId = "signInNewUser", description = "Sign In User")
    @PostMapping("/signin")
    public ResponseEntity<UserResponse> signIn(@RequestBody UserRequest request) {
        return ResponseEntity.ok(authService.SignIn(request));
    }
}
