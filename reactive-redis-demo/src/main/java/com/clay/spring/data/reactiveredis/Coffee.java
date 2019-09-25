package com.clay.spring.data.reactiveredis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {

    private Long id;

    private String name;

    private Long price;
}
