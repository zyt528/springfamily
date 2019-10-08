package com.clay.spring.mvc.simplecontrollerdemo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author clay
 */
@Getter
@Setter
@ToString
public class NewOrderRequest {

    private String customer;

    private List<String> items;
}
