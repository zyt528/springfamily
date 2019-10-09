package com.clay.spring.mvc.complexcontrollerdemo.repository;

import com.clay.spring.mvc.complexcontrollerdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuntzhao
 */

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    List<Coffee> findByNameInOrderById(List<String> list);

    Coffee findByName(String name);
}
