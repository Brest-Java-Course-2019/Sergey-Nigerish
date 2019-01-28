package com.epam.brest.cources.calculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class СalculationImpl implements Сalculation {
    private BigDecimal totalCost = BigDecimal.ZERO;
    private BigDecimal distance;
    private BigDecimal distanceCost;

    @Override
    public BigDecimal calculate(List<Map<Integer, BigDecimal>> price, BigDecimal[] data) {
        distanceCost = priceCost(price.get(1), data[1]);
        distance = data[1];
        priceCost(price.get(0), data[0], true);
        return totalCost;
    }

    private BigDecimal priceCost(Map<Integer, BigDecimal> price, BigDecimal value, boolean difference) {
        SortedSet<Integer> sortedKeys = new TreeSet<>(price.keySet());
        Integer valueMin = sortedKeys.first();

        if (difference) {
            Integer valueMax = sortedKeys.last();
            BigDecimal decMax = BigDecimal.valueOf(valueMax);
            while (value.doubleValue() > valueMax) {
                totalCost = totalCost.add(multyFour(decMax, price.get(valueMax), distance, distanceCost));
                value = value.subtract(decMax);
            }
        }

        for (Integer key : sortedKeys) {
            if (valueMin >= value.doubleValue()) {
                break;
            }
            valueMin = key;
        }

        if (difference) {
            totalCost = totalCost.add(multyFour(value, price.get(valueMin), distance, distanceCost));
            return totalCost;
        } else {
            return price.get(valueMin);
        }
    }

    private BigDecimal priceCost(Map<Integer, BigDecimal> price, BigDecimal value) {
        return priceCost(price, value, false);
    }

    private BigDecimal multyFour(BigDecimal one, BigDecimal two, BigDecimal three, BigDecimal four) {
        BigDecimal result = one.multiply(two);
        result = result.multiply(three);
        result = result.multiply(four);
        return result;
    }
}
