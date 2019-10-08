package com.clay.spring.mvc.simplecontrollerdemo.repository;

import org.joda.money.Money;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Money, Long> {
}
