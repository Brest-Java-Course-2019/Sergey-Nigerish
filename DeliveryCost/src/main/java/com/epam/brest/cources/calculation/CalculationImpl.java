package com.epam.brest.cources.calculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class CalculationImpl implements Calculation {
    private BigDecimal totalCost = BigDecimal.ZERO;
    private BigDecimal distance;
    private BigDecimal distanceRatio;

    @Override
    public BigDecimal calculateCost(List<Map<Integer, BigDecimal>> price, BigDecimal[] inputValues) {
        distanceRatio = calculateRatioAndCost(price.get(1), inputValues[1]);
        distance = inputValues[1];
        totalCost = calculateRatioAndCost(price.get(0), inputValues[0], true);
        return totalCost;
    }

    private BigDecimal calculateRatioAndCost(Map<Integer, BigDecimal> price, BigDecimal searchValue, boolean cost) {
        SortedSet<Integer> sortedKeys = new TreeSet<>(price.keySet());
        Integer valueMin = sortedKeys.first();

        if (cost) {
            Integer valueMax = sortedKeys.last();
            BigDecimal decimalMax = BigDecimal.valueOf(valueMax);
            while (searchValue.doubleValue() > valueMax) {
                totalCost = totalCost.add(multiplyFour(decimalMax, price.get(valueMax), distance, distanceRatio));
                searchValue = searchValue.subtract(decimalMax);
            }
        }

        for (Integer key : sortedKeys) {
            if (valueMin >= searchValue.doubleValue()) {
                break;
            }
            valueMin = key;
        }

        if (cost) {
            totalCost = totalCost.add(multiplyFour(searchValue, price.get(valueMin), distance, distanceRatio));
            return totalCost;
        } else {
            return price.get(valueMin);
        }
    }

    private BigDecimal calculateRatioAndCost(Map<Integer, BigDecimal> price, BigDecimal value) {
        return calculateRatioAndCost(price, value, false);
    }

    private BigDecimal multiplyFour(BigDecimal one, BigDecimal two, BigDecimal three, BigDecimal four) {
        BigDecimal result = one.multiply(two);
        result = result.multiply(three);
        result = result.multiply(four);
        return result;
    }
}
