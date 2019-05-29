package com.advanceio.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.advanceio.dao.SingletonFundRepository;
import com.advanceio.exception.BadRequestException;

@RunWith(MockitoJUnitRunner.class)
public class MoneyServiceTest {

    @Mock
    private SingletonFundRepository fundsRepository;

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
}
