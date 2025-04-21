package com.springBootLearning.Prod_ready_features.clients.impl;

import com.springBootLearning.Prod_ready_features.advices.ApiResponse;
import com.springBootLearning.Prod_ready_features.clients.EmployeeClient;
import com.springBootLearning.Prod_ready_features.dtos.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;


    @Override
    public List<EmployeeDto> getAllEmployees() {

        try {

            //as we are receiving the data in form of ApiResponse
            ApiResponse<List<EmployeeDto>> employeeDtoList =  restClient.get()
                    .uri("/employee")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeDtoList.getData();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }


    public EmployeeDto getEmployeeById(Long id) {

        try{

            ApiResponse<EmployeeDto> outputEmployee = restClient.get()
                    .uri("/employee/{employeeId}" , id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDto>>() {
                    });

            return outputEmployee.getData();

        } catch(Exception e){
            throw new RuntimeException(e);
        }

    }


    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto){

        try{

//            ApiResponse<EmployeeDto> newEmployee = restClient
//                    .post()
//                    .uri("/employee")
//                    .body(employeeDto)
//                    .retrieve()
//                    .onStatus(HttpStatusCode::is4xxClientError , (req , resp) -> {
//                        System.out.println("Client error : " + new String(resp.getBody().readAllBytes()));
//                        throw new RuntimeException("Could not create new employee");
//                    })
//                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDto>>() {
//                    });
//
//            return newEmployee.getData();

            //if want to get resposne as ResponseEntity like if we want to status code headers etc if only body then go with above method
            ResponseEntity<ApiResponse<EmployeeDto>> newEmployee = restClient
                    .post()
                    .uri("/employee")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , (req , resp) -> {
                        System.out.println("Client error : " + new String(resp.getBody().readAllBytes()));
                        throw new RuntimeException("Could not create new employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });

            return newEmployee.getBody().getData();

        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
