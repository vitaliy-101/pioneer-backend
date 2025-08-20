package com.example.pioneerbackend.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Перехват всех неперехваченных исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(
            Exception ex,
            WebRequest request
    ) {
        // Форматирование сообщения об ошибке
        String errorMessage = String.format(
                "Произошла ошибка: %s\nПричина: %s",
                ex.getClass().getSimpleName(),
                ex.getMessage()
        );

        // Логирование ошибки в консоль (можно заменить на logger)
        System.err.println("=== ОШИБКА ===");
        ex.printStackTrace();
        System.err.println("==============");

        // Возврат ответа с деталями ошибки
        return new ResponseEntity<>(
                errorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}