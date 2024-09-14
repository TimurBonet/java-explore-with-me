package ru.practicum.errorHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(Exception e) {
        log.error("500 {}", e.getMessage(), e);
        return new ErrorResponse("RuntimeException","INTERNAL_SERVER_ERROR",
                "Произошла непредвиденная ошибка", LocalDateTime.now());
    }
}