package com.clay.spring.data.springbucks;

import com.clay.spring.data.springbucks.model.Coffee;
import com.clay.spring.data.springbucks.model.CoffeeOrder;
import com.clay.spring.data.springbucks.model.OrderState;
import com.clay.spring.data.springbucks.repository.CoffeeRepository;
import com.clay.spring.data.springbucks.service.CoffeeOrderService;
import com.clay.spring.data.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Optional;

/**
 * @author clay
 */
@SpringBootApplication
@Slf4j
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableTransactionManagement
//@EnableCaching(proxyTargetClass = true)
public class SpringbucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

//    @Autowired
//    private JedisPool jedisPool;
//
//    @Autowired
//    private JedisPoolConfig jedisPoolConfig;

    public static void main(String[] args) {
        SpringApplication.run(SpringbucksApplication.class, args);
    }

//    @Bean
//    @ConfigurationProperties("redis")
//    public JedisPoolConfig jedisPoolConfig() {
//        return new JedisPoolConfig();
//    }
//
//    @Bean(destroyMethod = "close")
//    public JedisPool jedisPool(@Value("${redis.host}") String host) {
//        return new JedisPool();
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // TODO findAll 无法实现aop ？

        log.info("Count: {}", coffeeService.findAllCoffee().size());

        for (int i = 0; i < 10; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }

        coffeeService.reloadCoffee();
        log.info("Reading after refresh.");
        coffeeService.findAllCoffee().forEach(coffee -> log.info("Coffee: {}", coffee));

//        log.info(jedisPoolConfig.toString());
//
//        try (Jedis jedis = jedisPool.getResource()) {
//            coffeeRepository.findAll().forEach(coffee -> {
//                jedis.hset("springbucks-menu", coffee.getName(), Long.toString(coffee.getPrice().getAmountMinorLong()));
//            });
//
//            Map<String, String> menu = jedis.hgetAll("springbucks-menu");
//            log.info("Menu: {}", menu);
//            String price = jedis.hget("springbucks-menu", "espresso");
//            log.info("espresso - {}", Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
//        }

//        log.info("All Coffee: {}", coffeeRepository.findAll());
//
//        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
//        if (latte.isPresent()) {
//            CoffeeOrder order = orderService.createOrder("Li Lei", latte.get());
//            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
//            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
//        }
    }

}
