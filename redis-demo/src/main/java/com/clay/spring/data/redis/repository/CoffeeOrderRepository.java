package com.clay.spring.data.redis.repository;

import com.clay.spring.data.redis.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
