package com.clay.spring.reactivespringbucks.service;

import com.clay.spring.reactivespringbucks.model.CoffeeOrder;
import com.clay.spring.reactivespringbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author clay
 */

@Service
@Slf4j
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<Long> create(CoffeeOrder coffeeOrder) {
        return coffeeOrderRepository.save(coffeeOrder);
    }
}
