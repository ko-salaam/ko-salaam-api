package com.kosalaam.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class KosalaamApplication {

    public static void main(String[] args) {
        System.out.println("test");
        SpringApplication.run(KosalaamApplication.class, args
        );
    }
}
