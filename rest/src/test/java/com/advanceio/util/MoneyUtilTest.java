package com.advanceio.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.advanceio.entity.Money;
import com.advanceio.entity.Money.Type;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class MoneyUtilTest {

    @Test
    public void shouldReturnReversedValueOrderedList() {
        final int DEFAULT_AMOUNT_COINS = 20;
        final List<Money> FULLY_SORTED_ARRAY = Lists.newArrayList(
                new Money(Type.COIN, 1, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.5, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.2, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.1, DEFAULT_AMOUNT_COINS)
        );
        
        final List<Money> UNSORTED_ARRAY = Lists.newArrayList(
                new Money(Type.COIN, 0.5, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.1, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.2, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 1, DEFAULT_AMOUNT_COINS)
        );

        List<Money> arrayAfterSorting = MoneyUtil.sort(UNSORTED_ARRAY);

        for (int i = 0; i < arrayAfterSorting.size(); i++) {
            double EXPECTED_VALUE = FULLY_SORTED_ARRAY.get(i).getValue();
            double currentValue = arrayAfterSorting.get(i).getValue();
            assertEquals(EXPECTED_VALUE, currentValue, 0);
        }
    }
}
