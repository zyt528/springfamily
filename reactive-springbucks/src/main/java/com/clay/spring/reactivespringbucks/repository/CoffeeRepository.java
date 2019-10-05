package com.clay.spring.reactivespringbucks.repository;

import com.clay.spring.reactivespringbucks.model.Coffee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author clay
 */

@Repository
public interface CoffeeRepository extends R2dbcRepository<Coffee, Long> {

    /**
     * find a result by name
     * @param name
     * @return
     */
    @Query("select * from t_coffee where name=$1")
    Mono<Coffee> findByName(String name);
}
