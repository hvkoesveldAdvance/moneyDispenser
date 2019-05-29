package com.advanceio.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.advanceio.entity.Money;
import com.google.common.collect.Lists;

/**
 * This singleton could be replaced by using a database if need be
 * Using a database would for example prevent data loss
 */
@Service
@Scope("singleton")
public class SingletonFundRepository {

    private static final int INITIAL_NUMBER = 100;

    private Map<Double, Money> repository;

    @PostConstruct
    private void init() {
        List<Money> initialMoney = Lists.newArrayList(
                new Money(Money.Type.COIN, 0.01, INITIAL_NUMBER),
                new Money(Money.Type.COIN, 0.05, INITIAL_NUMBER),
                new Money(Money.Type.COIN, 0.10, INITIAL_NUMBER),
                new Money(Money.Type.COIN, 0.25, INITIAL_NUMBER),
                new Money(Money.Type.COIN, 0.75, INITIAL_NUMBER)
        );

        repository = initialMoney
                .stream()
                .collect(Collectors.toMap(Money::getValue, item -> item));
    }


    public Money create(Money newAdditionMoney) {
        repository.put(newAdditionMoney.getValue(), newAdditionMoney);
        return newAdditionMoney;
    }

    public Optional<Money> find(double valueID) {
        Money money = repository.get(valueID);
        return Optional.ofNullable(money);
    }
    
    public List<Money> findAll(){
        List<Money> currentFundsState = repository
                .values()
                .stream()
                .map(money -> {
                    return new Money(money.getType(), money.getValue(), money.getAmount());
                })
                .collect(Collectors.toList());
        return currentFundsState;
    }

    public void performWithdrawal(List<Money> moneyToWithdraw) {
        moneyToWithdraw.forEach(money -> {
            Double key = money.getValue();
            repository.get(key).withdraw(money.getAmount());
        });
    }



}
