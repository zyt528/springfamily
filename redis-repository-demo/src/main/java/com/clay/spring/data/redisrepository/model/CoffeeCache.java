package com.clay.spring.data.redisrepository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author clay
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "springbucks-coffee", timeToLive = 60)
public class CoffeeCache extends BaseEntity {

    @Id
    private Long id;

    @Indexed
    private String name;

    private Money price;
}
