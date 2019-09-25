package com.clay.spring.data.reactiveredis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yuntzhao
 */
@SpringBootApplication
@Slf4j
public class ReactiveRedisDemoApplication implements ApplicationRunner {

    private static final String KEY = "COFFEE_MENU";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        return new ReactiveStringRedisTemplate(reactiveRedisConnectionFactory);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRedisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ReactiveHashOperations<String, String, String> hashOperations = reactiveStringRedisTemplate.opsForHash();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        List<Coffee> list = jdbcTemplate.query("select * from t_coffee", (rs, i) -> Coffee.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getLong("price"))
                .build()
        );

        Flux.fromIterable(list)
                .publishOn(Schedulers.single())
                .doOnComplete(() -> log.info("List ok."))
                .flatMap(coffee -> {
                    log.info("try to put {}, {}", coffee.getName(), coffee.getPrice());
                    return hashOperations.put(KEY, coffee.getName(), coffee.getPrice().toString());
                })
                .doOnComplete(() -> log.info("Set ok."))
                .concatWith(reactiveStringRedisTemplate.expire(KEY, Duration.ofMinutes(1)))
                .doOnComplete(() -> log.info("Expire ok"))
                .onErrorResume(e -> {
                    log.error("Exception {}", e.getMessage());
                    return Mono.just(Boolean.FALSE);
                })
                .subscribe(
                        b -> log.info("Boolean: {}", b),
                        e -> log.error("Exception: {}", e.getMessage()),
                        () -> countDownLatch.countDown()
                );
        log.info("Waiting");
        countDownLatch.await();
    }
}
