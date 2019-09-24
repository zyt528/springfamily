package com.clay.spring.data.redisrepository.repository;

import com.clay.spring.data.redisrepository.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
