package com.advanceio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceio.dao.SingletonFundRepository;
import com.advanceio.entity.Money;
import com.advanceio.exception.BadRequestException;
import com.advanceio.util.Combination;
import com.advanceio.util.CombinationGroup;
import com.advanceio.util.CombinationGroupUtil;
import com.advanceio.util.MoneyUtil;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;

@Service
public class MoneyService {

    @Autowired
    private SingletonFundRepository funds;

    public List<Money> findAll() {
        return MoneyUtil.sort(funds.findAll());
    }

    public List<Money> withdraw(double change) {
        if (change <= 0) {
            throw new BadRequestException("Cannot withdraw no amount");
        }

        List<Money> currentFunds = MoneyUtil.sort(funds.findAll());

        List<Money> currentWithdrawStack = calculateChange(change, currentFunds, Lists.newArrayList(), 0);

        funds.performWithdrawal(currentWithdrawStack);

        return currentWithdrawStack;
    }

    public Money add(Money newAdditionMoney) {
        if (newAdditionMoney.getAmount() <= 0) {
            throw new BadRequestException("Amount of money to be added should be greater or equal to 1");
        }
        
        Optional<Money> currentMoneyState = funds.find(newAdditionMoney.getValue());
        
        if (currentMoneyState.isPresent()) {
            currentMoneyState.get().addFunds(newAdditionMoney.getAmount());
            return currentMoneyState.get();
        } else {
            return funds.create(newAdditionMoney);
        }
    }

    private List<Money> calculateChange(double change, List<Money> fundsState) {
        double [] denominatorsAvailable = findAllDenominators(fundsState);

        CombinationGroup allCombinations = CombinationGroupUtil.findCombinationGroup(denominatorsAvailable, change);
        
        allCombinations.sortAscending();

        for (Combination combination: allCombinations.getCombinations()) {
            if (canPerformWithdrawal (denominatorsAvailable, combination, fundsState)) {
                return createStackToBeReturned(combination, fundsState);
            }
        }

        throw new BadRequestException("Unable to withdraw this amount of money");
    }

    private List<Money> createStackToBeReturned(Combination combination, List<Money> fundsState) {
        List<Money> returnStack = Lists.newArrayList();
        int counter = 0;
        
        for (Integer amount: combination.getCombination()) {
            double actualValue = fundsState.get(counter).getValue();
            returnStack.add(new Money(Money.Type.COIN, actualValue, amount));
            counter++;
        }

        return returnStack;
    }
    
    /**
     * 0.5
     * 
     * 0.2 | 0.1 | 0.05
     */

    private boolean canPerformWithdrawal(double [] denominatorsAvailable, Combination combination, List<Money> fundsState) {
        boolean successfulWithdraw = true;
        for (int i = 0; i < combination.getCombination().size(); i++) {
            double denominatorValue = denominatorsAvailable[i];
            int amountNecessary = combination.getCombination().get(i);
            Money currentMoney = null;

            for (Money money : fundsState) {
                if (denominatorValue == money.getValue()) {
                    currentMoney = money;
                }
            }
           
            if(currentMoney.getAmount() < amountNecessary) {
                successfulWithdraw = false;
            }
        }

        return successfulWithdraw;
    }

    private double[] findAllDenominators(List<Money> currentWithdrawStack) {
        List<Double> currentWithdrawDenominators = Lists.newArrayList();
        currentWithdrawStack.forEach(money -> {
//            if (money.getAmount() > 0) {
                currentWithdrawDenominators.add(money.getValue());
//            }
        });

        /// denominatorsAvailable = {0.25, 0.1, 0.05}
        double [] denominatorsAvailable = currentWithdrawDenominators
                .stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
        return denominatorsAvailable;
    }

    /**
     * @Deprecated This function is no longer in use and does not function as it should with regards to different denominators
     */
    private List<Money> calculateChange(double change, List<Money> fundsState, List<Money> currentWithdrawStack, int index) {
        // base case
        if (change == 0) {
            return currentWithdrawStack;
        }

        if (index == fundsState.size()) {
            throw new BadRequestException("The machine does not have the required money available.");
        }

        Money currentMoney = fundsState.get(index);
        int maximumCurrentMoneyWithdrawable = calculateMaximumWithdrawable(change, currentMoney.getValue(), currentMoney.getAmount());

        if (maximumCurrentMoneyWithdrawable > 0) {
            currentWithdrawStack.add(new Money(currentMoney.getType(), currentMoney.getValue(), maximumCurrentMoneyWithdrawable));
        }
        double actualAmountWithdrawn = maximumCurrentMoneyWithdrawable * currentMoney.getValue();

        return calculateChange(change - actualAmountWithdrawn, fundsState, currentWithdrawStack, index + 1);
    }

    @VisibleForTesting
    protected int calculateMaximumWithdrawable(double change, double value, int actualCurrentAmountLeft) {
        int amountForCurrentMoneyNeeded = (new Double(Math.floor(change / value))).intValue();
        return amountForCurrentMoneyNeeded > actualCurrentAmountLeft ? actualCurrentAmountLeft : amountForCurrentMoneyNeeded;
    }
}

