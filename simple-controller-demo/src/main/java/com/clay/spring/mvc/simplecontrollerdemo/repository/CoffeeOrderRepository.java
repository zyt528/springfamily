package com.clay.spring.mvc.simplecontrollerdemo.repository;

import com.clay.spring.mvc.simplecontrollerdemo.model.CoffeeOrder;
import org.joda.money.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
