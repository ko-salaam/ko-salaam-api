package com.kosalaam.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class KosalaamApplication {

    public static void main(String[] args) {
        SpringApplication.run(KosalaamApplication.class, args
        );
    }
}
