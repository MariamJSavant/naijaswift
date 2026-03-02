package com.naijaswift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.naijaswift"})
public class NaijaSwiftApplication {
    public static void main(String[] args) {
        SpringApplication.run(NaijaSwiftApplication.class, args);
    }
}
