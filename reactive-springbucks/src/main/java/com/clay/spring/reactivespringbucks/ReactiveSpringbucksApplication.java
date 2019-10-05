package com.clay.spring.reactivespringbucks;

import com.clay.spring.reactivespringbucks.converter.MoneyReadConverter;
import com.clay.spring.reactivespringbucks.converter.MoneyWriteConverter;
import com.clay.spring.reactivespringbucks.model.Coffee;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

/**
 * @author yuntzhao
 */

@SpringBootApplication
@EnableR2dbcRepositories
@Slf4j
public class ReactiveSpringbucksApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringbucksApplication.class, args);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .inMemory("testdb")
                        .username("sa")
                        .build()
        );
    }

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return new R2dbcCustomConversions(Arrays.asList(new MoneyWriteConverter(), new MoneyReadConverter()));
    }

    @Bean
    public ReactiveRedisTemplate<String, Coffee> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Coffee> valueSerializer = new Jackson2JsonRedisSerializer<>(Coffee.class);

        RedisSerializationContext<String, Coffee> context = RedisSerializationContext.<String, Coffee>newSerializationContext()
                .key(keySerializer)
                .value(valueSerializer).build();
        return new ReactiveRedisTemplate<String, Coffee>(reactiveRedisConnectionFactory, context);
    }
}
