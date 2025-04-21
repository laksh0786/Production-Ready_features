package com.springBootLearning.Prod_ready_features.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

//✅ What is RestClient?
//RestClient is a new HTTP client introduced in Spring Framework 6 / Spring Boot 3.2.

//The RestClient in Spring Boot is used to:
//✅ Make HTTP requests (GET, POST, PUT, DELETE) to external REST APIs from your Java Spring Boot application.

//It is the modern alternative to RestTemplate, offering:
//Fluent API
//Better integration
//Simplified code
//No more builders or verbose boilerplate
//🔥 It's like WebClient but easier to use — and finally here to replace RestTemplate.

@Configuration
public class RestClientConfig {


    //@Value is a Spring annotation used to inject values from:
    //application.properties or application.yml
    //System/environment variables
    //Expressions or defaults
    //It's part of Spring's Dependency Injection (DI) system for injecting literal values into your beans or components.

    @Value("${employeeService.base.url}")
    private String BASE_URL;

    //What is @Qualifier?
    //@Qualifier is a Spring annotation used alongside @Autowired to resolve conflicts when there are multiple beans of the same type.
    //It tells Spring which exact bean to inject when it’s confused.

    @Bean
    @Qualifier("employeeRestClient")
    RestClient getEmployeeRestClient(){

        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                // for handling the error in same way on different requests and for different handling check the create new employee in EmployeeClientConfig
                .defaultStatusHandler(HttpStatusCode::is5xxServerError , (req , resp) -> {
                    throw new RuntimeException("Server error occured...");
                })
                .build();

    }

}
