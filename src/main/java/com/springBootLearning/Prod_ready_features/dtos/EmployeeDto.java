package com.springBootLearning.Prod_ready_features.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private Long id;

    //    @NotNull(message = "Required field : name")  //not null only  -> issue name = ""
//    @NotEmpty(message = "Name must not be empty")  //not null + length > 0  --> issue name = "    "
    @NotBlank(message = "Name must not be blank") //not null + trimmed length greater than 0 working fine
    @Size(min = 3 , max = 10 , message = "name must be between 3 to 10 characters")
    private String name;

    @NotBlank(message = "Email is mandatory field")
    @Email(message = "Email should be in valid format..")
    private String email;

    @NotNull(message = "Age is mandatory field")
    @Max(value = 80 , message = "Age cannot be greater than 80")
    @Min(value= 18 , message = "Age cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role is mandatory field")
//    @Pattern(regexp = "^(ADMIN|USER)$" , message = "Role can be only USER or ADMIN")
    private String role;

    @NotNull(message = "Salary is a mandatory field")
//    @Positive(message = "Salary must be greater than 0")
    @PositiveOrZero(message = "Salary must be greater than or equal to 0") //similar for negative values
    @Digits(integer = 6, fraction = 2 , message = "salary must have 6 integers and 2 decimal")
    @DecimalMax(value = "100000.65" , message = "salary cannot exceed the provided value")
    @DecimalMin(value = "100.10" , message = "salary must be greater than 100.10")
    private Double salary;

    //    @Past()  //date is in past
    @PastOrPresent(message = "Date of joining cannot be in future") //date is in present or past similar for future
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active") //value must be true same for AssertFalse the value must be false
    @JsonProperty("isActive")
    private Boolean isActive;

}

