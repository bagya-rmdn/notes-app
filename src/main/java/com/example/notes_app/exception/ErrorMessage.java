package com.example.notes_app.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorMessage {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Object message;
    private String path;
}
