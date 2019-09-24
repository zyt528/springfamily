package com.clay.spring.data.redisrepository.repository;

import com.clay.spring.data.redisrepository.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author clay
 */

public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {
    Optional<CoffeeCache> findOneByName(String name);
}
