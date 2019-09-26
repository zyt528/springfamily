package com.clay.spring.data.reactivemongo.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

public class MoneyWriteConverter implements Converter<Money, Long> {
    @Override
    public Long convert(Money source) {
        return source.getAmountMinorLong();
    }
}
