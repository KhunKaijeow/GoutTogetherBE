package dev.kjcoder.goutbackend.common;

import dev.kjcoder.goutbackend.common.exception.EntityNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResponseAdviceHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseAdviceHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var text = "Invalid argument request";
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                text
        );
        Map<String, Object> properties = new HashMap<>();
        var invalidArgumentList = e.getBindingResult().getFieldErrors();
        for (var oe : invalidArgumentList) {
            properties.put(oe.getField(), oe.getDefaultMessage());
        }
        detail.setProperties(properties);
        return ResponseEntity.badRequest().body(detail);
    }

    @ExceptionHandler(EntityNotFound.class)
    protected ResponseEntity<?> entityNotFoundHandler(EntityNotFound e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        logger.info("Entity not found: {}", detail);
        return ResponseEntity.notFound().build();
    }

    // Global exception handler
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> globalExceptionHandler(Exception e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
        logger.error("Internal server error: {}", e.getMessage());
        return ResponseEntity.internalServerError().body(detail);
    }
}
