package com.advanceio.util;

import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

public class CombinationGroup {

    private List<Combination> combinations = Lists.newArrayList();
    
    /**
     * 
2019-05-29 14:54:31.401  INFO 6732 --- [nio-8080-exec-5] com.advanceio.util.CombinationGroupUtil  : Denominators: [0.25, 0.1, 0.05, 0.01]
2019-05-29 14:54:31.401  INFO 6732 --- [nio-8080-exec-5] com.advanceio.util.CombinationGroupUtil  : Possibility: [0, 0, 0, 30]
2019-05-29 14:54:31.401  INFO 6732 --- [nio-8080-exec-5] com.advanceio.util.CombinationGroupUtil  : Possibility: [0, 0, 1, 25]

{
        List<Combination> combinations = [
                [0, 0, 0, 30] = 30
                [0, 0, 1, 25] = 26
        ]
}
     */

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