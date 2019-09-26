package com.clay.spring.data.reactivemongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * @author yuntzhao
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {

    private String id;

    private String name;

    private Money price;

    private Date updateTime;

    private Date createTime;
}
