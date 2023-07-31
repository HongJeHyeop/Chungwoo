package com.transport.cw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CwApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwApplication.class, args);
    }

}
