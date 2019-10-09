package com.clay.spring.mvc.complexcontrollerdemo.repository;

import com.clay.spring.mvc.complexcontrollerdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuntzhao
 */
@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
