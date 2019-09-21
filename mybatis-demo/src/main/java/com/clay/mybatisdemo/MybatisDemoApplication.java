package com.clay.mybatisdemo;

import com.clay.mybatisdemo.mapper.CoffeeMapper;
import com.clay.mybatisdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author clay
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.clay.mybatisdemo.mapper")
public class MybatisDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee coffee = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();

        int count = coffeeMapper.save(coffee);
        log.info("Save {} Coffee: {}", count, coffee);

        coffee = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        count = coffeeMapper.save(coffee);
        log.info("Save {} Coffee: {}", count, coffee);

        coffee = coffeeMapper.findById(coffee.getId());
        log.info("Find Coffee: {}", coffee);
    }
}
