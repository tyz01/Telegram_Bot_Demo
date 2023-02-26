package com.example.belarusattractions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BelarusAttractionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelarusAttractionsApplication.class, args);
    }
}
