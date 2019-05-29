package com.advanceio.util;

import java.util.Comparator;
import java.util.List;

import com.advanceio.entity.Money;

public class MoneyUtil {

    public static final List<Money> sort(List<Money> currentFunds) {
        // can swap sorted order to get either more, or less number of items
        currentFunds.sort(Comparator.comparing(Money::getValue).reversed());
        return currentFunds;
    }

}
