package com.restaurant.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServerApplication.class, args);
    }

}
