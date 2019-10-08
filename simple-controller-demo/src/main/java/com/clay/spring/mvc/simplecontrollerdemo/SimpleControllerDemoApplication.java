package com.clay.spring.mvc.simplecontrollerdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author clay
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@Slf4j
public class SimpleControllerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleControllerDemoApplication.class, args);
    }

}
