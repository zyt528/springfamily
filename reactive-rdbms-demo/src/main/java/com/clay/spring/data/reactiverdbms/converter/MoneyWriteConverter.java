package com.clay.spring.data.reactiverdbms.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @author yuntzhao
 */
public class MoneyWriteConverter implements Converter<Money, Long> {
    @Override
    public Long convert(Money money) {
        return money.getAmountMinorLong();
    }
}
