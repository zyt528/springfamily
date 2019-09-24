package com.clay.spring.data.redis.repository;

import com.clay.spring.data.redis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
