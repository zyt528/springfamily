package com.clay.spring.mvc.complexcontrollerdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author yuntzhao
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@Slf4j
public class ComplexControllerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexControllerDemoApplication.class, args);
    }

}
