package com.attech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 */
@Configuration
@SpringBootApplication
public class ActiveMQApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMQApplicationMain.class, args);
    }
}
