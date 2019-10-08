package com.clay.spring.reactivespringbucks.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author clay
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder implements Serializable {
    private Long id;
    private String customer;
    private OrderState state;
    private List<Coffee> items;
    private Date createTime;
    private Date updateTime;
}
