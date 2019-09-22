package com.clay.spring.data.springbucks.repository;

import com.clay.spring.data.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clay
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
