package com.clay.spring.mvc.simplecontrollerdemo.repository;

import com.clay.spring.mvc.simplecontrollerdemo.model.Coffee;
import org.joda.money.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    List<Coffee> findByNameInOrderById(List<String> list);
}
