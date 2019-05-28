package com.advanceio.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceio.dao.SingletonFundRepository;
import com.advanceio.entity.Money;
import com.google.common.collect.Lists;

@Service
public class MoneyService {

    @Autowired
    private SingletonFundRepository funds;

    public List<Money> findAll() {
        return getSortedCurrentFunds();
    }

    public List<Money> withdraw(double change) {
        List<Money> currentFunds = getSortedCurrentFunds();

        List<Money> currentWithdrawStack = calculateChange(change, currentFunds, Lists.newArrayList(), 0);

        funds.performWithdrawal(currentWithdrawStack);

        return currentWithdrawStack;
    }

    private List<Money> calculateChange(double change, List<Money> fundsState, List<Money> currentWithdrawStack, int index) {
        // base case
        if (change == 0) {
            return currentWithdrawStack;
        }

        Money currentMoney = fundsState.get(index);
        int maximumCurrentMoneyWithdrawable = calculateMaximumWithdrawable(change, currentMoney.getValue(), currentMoney.getAmount());

        currentWithdrawStack.add(new Money(currentMoney.getType(), currentMoney.getValue(), maximumCurrentMoneyWithdrawable));
        double actualAmountWithdrawn = maximumCurrentMoneyWithdrawable * currentMoney.getValue();

        return calculateChange(change - actualAmountWithdrawn, fundsState, currentWithdrawStack, index + 1);
    }

    private int calculateMaximumWithdrawable(double change, double value, int currentAmountLeft) {
        int amountForCurrentMoneyNeeded = (new Double(Math.floor(change / value))).intValue();
        return amountForCurrentMoneyNeeded > currentAmountLeft ? currentAmountLeft : amountForCurrentMoneyNeeded;
    }

    private List<Money> getSortedCurrentFunds() {
        List<Money> currentFunds = funds.findAll();

        // can swap sorted order to get either more, or less number of items
        currentFunds.sort(Comparator.comparing(Money::getValue).reversed());

        return currentFunds;
    }

}
