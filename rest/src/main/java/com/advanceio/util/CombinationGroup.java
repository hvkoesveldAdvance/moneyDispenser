package com.advanceio.util;

import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

public class CombinationGroup {

    private List<Combination> combinations = Lists.newArrayList();

    public CombinationGroup() {
    }

    public void addCombination(List<Integer> combination) {
        combinations.add(new Combination(combination));
    }

    public List<Combination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Combination> combinations) {
        this.combinations = combinations;
    }

    public void sortAscending() {
        Comparator<Combination> byTotalAmount = Comparator.comparing(Combination::getTotalAmount);
        combinations.sort(byTotalAmount);
    }

    public void sortDescending() {
        Comparator<Combination> byTotalAmount = Comparator.comparing(Combination::getTotalAmount).reversed();
        combinations.sort(byTotalAmount);
    }
}