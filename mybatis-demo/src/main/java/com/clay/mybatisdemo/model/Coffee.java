package com.clay.mybatisdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * @author clay
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {

    private Long id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
