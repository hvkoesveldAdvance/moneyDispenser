package com.advanceio.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Ints;

public class CombinationGroupUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(CombinationGroupUtil.class);

    /**
     * Returns a list of combinations for all the denominators and the target
     */
    public static CombinationGroup findCombinationGroup(double[] denoms, double target) {
        CombinationGroup combinationGroup = new CombinationGroup();
        int[] vals = new int[denoms.length];
        final int startIndex = 0;
        LOGGER.info("Denominators: {}", denoms);
        calculatePossibilities(startIndex, denoms, target, vals, combinationGroup);
        return combinationGroup;
    }

    public static void calculatePossibilities(int index, double[] denom, double target, int[] vals, CombinationGroup combinationGroup) {
        if (target == 0) {
            LOGGER.info("Possibility: {}", Ints.asList(vals));
            combinationGroup.addCombination(Ints.asList(vals));
            return;
        }

        if (index == denom.length) {
            return;
        }

        double currDenom = denom[index];
        for (int i = 0; i * currDenom <= target; i++) {
            vals[index] = i;
            calculatePossibilities(index + 1, denom, target - i * currDenom, vals, combinationGroup);
            vals[index] = 0;
        }
    }
}
