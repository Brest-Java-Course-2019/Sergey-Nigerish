package com.epam.brest.cources;

import java.math.BigDecimal;
import java.util.Map;

public class getTotalCost {
    private Map<Byte, BigDecimal> price;
    private BigDecimal weight;
    private BigDecimal distance;
    private int size;

    public BigDecimal calcTotalCost(Map<Byte, BigDecimal> price, BigDecimal weight, BigDecimal distance) {
        this.price = price;
        this.weight = weight;
        this.distance = distance;
        this.size = price.size();
        byte[] list = new byte[size];
        int i = size;

        BigDecimal TotalCost = new BigDecimal("0");

        for (Byte aByte : price.keySet()) {
            i--;
            list[i] = aByte;
        }

        BigDecimal maxCost = price.get(list[0]); // max = cost of maximal weight
        BigDecimal maxWeight = toBig(list[0]);
        while (more(weight, maxWeight)) { //weight > maxWeight
            TotalCost = TotalCost.add(multThree(maxCost, distance, maxWeight)); //TotalCost = TotalCost + maxCost * distance * maxWeight
            weight = weight.subtract(maxWeight);
        }

        i = size -1;
        while (weight.doubleValue() > list[i]) {
            i--;
        }
        TotalCost = TotalCost.add(multThree(weight, distance, price.get(list[i]))); //TotalCost = TotalCost + weight * distance * price

        return TotalCost;
    }
    
    private BigDecimal toBig(long val) {
        return BigDecimal.valueOf(val);
    }

    private BigDecimal multThree(BigDecimal one, BigDecimal two, BigDecimal three) {
        BigDecimal result = one.multiply(two);
        return result.multiply(three);
    }

    private Boolean more(BigDecimal one, BigDecimal two) {     //true if one > two
        return one.compareTo(two) == 1;
    }
}
