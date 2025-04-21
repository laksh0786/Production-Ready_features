package com.springBootLearning.Prod_ready_features;

import com.springBootLearning.Prod_ready_features.clients.EmployeeClient;
import com.springBootLearning.Prod_ready_features.dtos.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(3) //using this we can set the order how the testcases will be running
	void getAllEmployeesTest(){

		List<EmployeeDto> employeeDtoList = employeeClient.getAllEmployees();

		System.out.println(employeeDtoList);

	}

	@Test
	@Order(2)
	void getEmployeeByIdTest(){

		EmployeeDto employeeDto = employeeClient.getEmployeeById(852L);

		System.out.println(employeeDto);

	}

	@Test
	@Order(1)
	void createNewEmployeeTest(){

		EmployeeDto inputEmployeeDto = new EmployeeDto(
				null ,
				"Lakshay",
				"lakshay@gmail.com",
				2,
				"ADMIN",
				105.0,
				LocalDate.of(2024 , 1 , 23),
				true
		);

		EmployeeDto savedEmployeeDto = employeeClient.createNewEmployee(inputEmployeeDto);

		System.out.println(savedEmployeeDto);

	}

}
