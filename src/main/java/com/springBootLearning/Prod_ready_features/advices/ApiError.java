package com.springBootLearning.Prod_ready_features.advices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;

    private HttpStatus httpStatus;

    private List<String> subErrors;

}
