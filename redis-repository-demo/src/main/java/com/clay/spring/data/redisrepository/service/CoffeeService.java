package com.clay.spring.data.redisrepository.service;

import com.clay.spring.data.redisrepository.model.Coffee;
import com.clay.spring.data.redisrepository.model.CoffeeCache;
import com.clay.spring.data.redisrepository.repository.CoffeeCacheRepository;
import com.clay.spring.data.redisrepository.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @author clay
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "coffee")
public class CoffeeService {

    private static final String CACHE = "springbucks-coffee";

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeCacheRepository cacheRepository;

    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findSimpleCoffeeFromCache(String name) {
        Optional<CoffeeCache> cached = cacheRepository.findOneByName(name);
        if (cached.isPresent()) {
            CoffeeCache coffeeCache = cached.get();
            Coffee coffee = Coffee.builder()
                    .name(coffeeCache.getName())
                    .price(coffeeCache.getPrice())
                    .build();
            log.info("Coffee found in cache: {}", coffee);
            return Optional.of(coffee);
        } else {
            Optional<Coffee> coffee = findOneCoffee(name);
            coffee.ifPresent(c -> {
                CoffeeCache coffeeCache = CoffeeCache.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .price(c.getPrice())
                        .build();
                log.info("Save Coffee {} to cache.", coffeeCache);
                cacheRepository.save(coffeeCache);
            });
            return coffee;
        }
    }

    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase(true));
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), exampleMatcher));
        log.info("Coffee found: {}", coffee);
        return coffee;
    }
}
