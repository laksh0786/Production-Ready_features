package com.springBootLearning.Prod_ready_features.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 2, max = 15 , message = "Title must be between 2 to 15")
    private String title;

    @Size(min = 10, max = 100 , message = "Description must be between 10 to 100")
    @NotBlank(message = "Description cannot be empty")
    private String description;

}
