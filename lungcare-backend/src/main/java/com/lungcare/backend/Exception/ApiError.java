package com.lungcare.backend.Exception;

import lombok.Data;
import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder

public class ApiError {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
}
