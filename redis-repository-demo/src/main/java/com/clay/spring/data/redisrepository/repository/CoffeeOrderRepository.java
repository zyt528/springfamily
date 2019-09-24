package com.clay.spring.data.redisrepository.repository;

import com.clay.spring.data.redisrepository.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
