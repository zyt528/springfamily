package com.clay.spring.reactivespringbucks.repository;

import com.clay.spring.reactivespringbucks.model.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author clay
 */

@Repository
public class CoffeeOrderRepository {

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<Long> save(CoffeeOrder coffeeOrder) {
        return databaseClient.insert().into("t_order")
                .value("customer", coffeeOrder.getCustomer())
                .value("state", coffeeOrder.getState())
                .value("create_time", coffeeOrder.getCreateTime())
                .value("update_time", coffeeOrder.getUpdateTime())
                .fetch()
                .first()
                .flatMap(m -> Mono.just((Long) m.get("ID")))
                .flatMap(id -> Flux.fromIterable(coffeeOrder.getItems())
                        .flatMap(c -> databaseClient.insert().into("t_order_coffee")
                                .value("coffee_order_id", id)
                                .value("items_id", c.getId())
                                .then()
                        ).then(Mono.just(id))
                );
    }

}
