package com.clay.spring.reactivespringbucks;

import com.clay.spring.reactivespringbucks.model.Coffee;
import com.clay.spring.reactivespringbucks.model.CoffeeOrder;
import com.clay.spring.reactivespringbucks.model.OrderState;
import com.clay.spring.reactivespringbucks.service.CoffeeOrderService;
import com.clay.spring.reactivespringbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author yuntzhao
 */

@SpringBootApplication
@Slf4j
public class ReactiveSpringbucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringbucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        coffeeService.initCache()
                .then(coffeeService.findOneCoffee("mocha")
                        .flatMap(c -> {
                            CoffeeOrder order = createOrder("LiLei", c);
                            return coffeeOrderService.create(order);
                        })
                        .doOnError(t -> log.error("error", t))
                )
                .subscribe(o -> log.info("Create Order: {}", o));

        log.info("After Subscribe");
        Thread.sleep(5000);
    }

    private CoffeeOrder createOrder(String customer, Coffee... coffee) {
        return CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffee))
                .state(OrderState.INIT)
                .build();
    }
}
