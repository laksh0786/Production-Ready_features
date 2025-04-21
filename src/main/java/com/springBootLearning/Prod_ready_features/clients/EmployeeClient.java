package com.springBootLearning.Prod_ready_features.clients;

import com.springBootLearning.Prod_ready_features.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);

}
