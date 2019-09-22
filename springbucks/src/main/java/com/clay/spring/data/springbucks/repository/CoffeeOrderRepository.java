package com.clay.spring.data.springbucks.repository;

import com.clay.spring.data.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
