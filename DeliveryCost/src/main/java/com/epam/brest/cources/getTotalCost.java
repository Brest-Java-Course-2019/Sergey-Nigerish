package com.epam.brest.cources;

import java.math.BigDecimal;
import java.util.Map;

public class getTotalCost {
    private Map<Byte, BigDecimal> price;
    private BigDecimal weight;
    private BigDecimal distance;
    private int i;
    private byte[] list = new byte[5];

    public BigDecimal calcTotalCost(Map<Byte, BigDecimal> price, BigDecimal weight, BigDecimal distance) {
        this.price = price;
        this.weight = weight;
        this.distance = distance;
        this.i = price.size();

        BigDecimal TotalCost = new BigDecimal("0");

        list[i] = 0;
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
        i = 0;

        while (!(less_or_equal(weight, toBig(list[i])) && more(weight, toBig(list[i + 1])))) { // while !(list[i] >= weight > list[i + 1])
            i++;
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

    private Boolean less_or_equal(BigDecimal one, BigDecimal two) {     //true if one =< two
        return (one.compareTo(two) == -1) || (one.compareTo(two) == 0);
    }

    private Boolean more(BigDecimal one, BigDecimal two) {     //true if one > two
        return one.compareTo(two) == 1;
    }
}
