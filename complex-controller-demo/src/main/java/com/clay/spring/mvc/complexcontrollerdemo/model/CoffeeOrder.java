package com.clay.spring.mvc.complexcontrollerdemo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author yuntzhao
 */
@Entity
@Data
@Table(name = "T_ORDER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CoffeeOrder extends BaseEntity {

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
