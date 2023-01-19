package com.eteacher.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
//
//@EnableEurekaClient
@EnableFeignClients
//@EnableCaching
@SpringBootApplication
public class EteacherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EteacherServiceApplication.class, args);
    }

    @Bean
    public ValidatorFactory validatorFactory(){
        return Validation.buildDefaultValidatorFactory();
    }
}
