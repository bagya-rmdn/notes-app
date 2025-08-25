package com.example.notes_app.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends RuntimeException {
    private final int statusCode;

    public static final int BAD_REQUEST_STATUS_CODE = 400;
    public static final int UNAUTHORIZED_STATUS_CODE = 401;
    public static final int FORBIDDEN_STATUS_CODE = 403;
    public static final int NOT_FOUND_STATUS_CODE = 404;
    public static final int INTERNAL_ERROR_STATUS_CODE = 500;

    protected HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(this.statusCode);
    }

    public ServiceException(String message) {
        super(message);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public ServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
