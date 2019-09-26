package com.clay.spring.data.reactiverdbms.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @author yuntzhao
 */
public class MoneyReadConverter implements Converter<Long, Money> {
    @Override
    public Money convert(Long aLong) {
        return Money.of(CurrencyUnit.of("CNY"), aLong);
    }
}
