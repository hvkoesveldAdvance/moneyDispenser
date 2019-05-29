package com.advanceio.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.advanceio.dao.SingletonFundRepository;
import com.advanceio.entity.Money;
import com.advanceio.entity.Money.Type;
import com.advanceio.exception.BadRequestException;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class MoneyServiceTest {

    @Mock
    private SingletonFundRepository funds;

    @InjectMocks
    private MoneyService moneyService;

    @Test
    public void shouldFailOnNoChangeWithdrawal() {
        try {
            moneyService.withdraw(0);
            fail("Must not be able to withdraw 0 value change");
        } catch (BadRequestException ex) {
            assertTrue(Boolean.TRUE);
        }
    }

    @Test
    public void shouldCalculateSameAmountAvailableToWithdrawAsCurrent() {
        final double MONEY_VALUE = 0.5;
        final double VALUE_CHANGE_LEFT = 20;
        final int ACTUAL_CURRENT_AMOUNT_LEFT = 10;
        int maxWithdrawable = moneyService.calculateMaximumWithdrawable(VALUE_CHANGE_LEFT, MONEY_VALUE, ACTUAL_CURRENT_AMOUNT_LEFT);
        assertTrue(maxWithdrawable == 10);
    }

    @Test
    public void shouldDefaultToActualCurrentAmountIfMoreIsAsked() {
        final double MONEY_VALUE = 0.5;
        final double VALUE_CHANGE_LEFT = 20;
        final int ACTUAL_CURRENT_AMOUNT_LEFT = 5;

        int maxWithdrawable = moneyService.calculateMaximumWithdrawable(VALUE_CHANGE_LEFT, MONEY_VALUE, ACTUAL_CURRENT_AMOUNT_LEFT);
        assertTrue(maxWithdrawable == ACTUAL_CURRENT_AMOUNT_LEFT);
    }

    @Test
    public void shouldDefaultToWithdrawAmountIfCurrentAmountIsMoreThanAsked() {
        final double MONEY_VALUE = 0.5;
        final double VALUE_CHANGE_LEFT = 20;
        final int ACTUAL_CURRENT_AMOUNT_LEFT = 50;

        final int maxAmountAUserCanWithdraw = (new Double(VALUE_CHANGE_LEFT / MONEY_VALUE)).intValue();

        int maxWithdrawable = moneyService.calculateMaximumWithdrawable(VALUE_CHANGE_LEFT, MONEY_VALUE, ACTUAL_CURRENT_AMOUNT_LEFT);
        assertTrue(maxWithdrawable == maxAmountAUserCanWithdraw);
    }
    
    @Test
    public void shouldReturnReversedValueOrderedList() {
        final int DEFAULT_AMOUNT_COINS = 20;
        final List<Money> FULLY_SORTED_ARRAY = Lists.newArrayList(
                new Money(Type.COIN, 1, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.5, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.2, DEFAULT_AMOUNT_COINS),
                new Money(Type.COIN, 0.1, DEFAULT_AMOUNT_COINS)
        );

        Mockito.when(funds.findAll()).thenReturn(
                Lists.newArrayList(
                        new Money(Type.COIN, 0.5, DEFAULT_AMOUNT_COINS),
                        new Money(Type.COIN, 0.1, DEFAULT_AMOUNT_COINS),
                        new Money(Type.COIN, 0.2, DEFAULT_AMOUNT_COINS),
                        new Money(Type.COIN, 1, DEFAULT_AMOUNT_COINS)
                )
        );

        List<Money> arrayAfterSorting = moneyService.getSortedCurrentFunds();

        for (int i = 0; i < arrayAfterSorting.size(); i++) {
            double EXPECTED_VALUE = FULLY_SORTED_ARRAY.get(i).getValue();
            double currentValue = arrayAfterSorting.get(i).getValue();
            assertEquals(EXPECTED_VALUE, currentValue, 0);
        }
    }
}
