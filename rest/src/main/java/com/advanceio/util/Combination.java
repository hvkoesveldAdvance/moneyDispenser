package com.advanceio.util;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<Integer> combination = new ArrayList<>();
    private int totalAmount = 0;

    protected Combination() {
        super();
    }

    public Combination(List<Integer> combinationList) {
        super();
        combination.addAll(combinationList);
        combinationList.forEach(amountPerCoin -> {
            totalAmount += amountPerCoin;
        });
    }

    public List<Integer> getCombination() {
        return combination;
    }

    public void setCombination(List<Integer> combination) {
        this.combination = combination;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

}