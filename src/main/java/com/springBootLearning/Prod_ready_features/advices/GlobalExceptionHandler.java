package com.springBootLearning.Prod_ready_features.advices;

import com.springBootLearning.Prod_ready_features.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> argsExceptionHandler(MethodArgumentNotValidException ex){

        List<String> subErrors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError
                .builder()
                .message("Input Validation Error")
                .subErrors(subErrors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return transformExceptionResponse(apiError);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> exceptionHandler(Exception ex){

        ApiError apiError = ApiError.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return transformExceptionResponse(apiError);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> exceptionHandler(ResourceNotFoundException ex){

        ApiError apiError = ApiError.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();

        return transformExceptionResponse(apiError);
    }


    private ResponseEntity<ApiResponse<?>> transformExceptionResponse(ApiError apiError) {

        return new ResponseEntity<>( new ApiResponse<>(apiError) , apiError.getHttpStatus());

    }

}
