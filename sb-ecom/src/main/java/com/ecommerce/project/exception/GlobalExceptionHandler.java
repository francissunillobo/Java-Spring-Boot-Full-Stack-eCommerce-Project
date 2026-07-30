package com.ecommerce.project.exception;

import com.ecommerce.project.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@ExceptionHandler // to handle specific handle exception
@RestControllerAdvice  //  Any exception from the controller and from Rest APIs
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // to handle specific exception
    public ResponseEntity<Map<String, String>> methodArgumentExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundException(ResourceNotFoundException e) {
        APIResponse apiResponse = new APIResponse(e.getMessage(), false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> apiExceptionHandler(APIException e) {
        APIResponse apiResponse = new APIResponse(e.getMessage(), false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
