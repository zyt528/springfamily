package com.clay.spring.mvc.complexcontrollerdemo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yuntzhao
 */
@Getter
@Setter
@ToString
public class NewCoffeeRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Money price;
}
