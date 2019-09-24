package com.clay.spring.data.cachewithredis.repository;

import com.clay.spring.data.cachewithredis.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
