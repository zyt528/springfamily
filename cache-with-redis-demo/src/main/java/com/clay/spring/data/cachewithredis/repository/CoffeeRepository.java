package com.clay.spring.data.cachewithredis.repository;

import com.clay.spring.data.cachewithredis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
