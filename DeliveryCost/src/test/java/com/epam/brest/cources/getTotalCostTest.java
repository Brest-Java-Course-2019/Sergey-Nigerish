package com.epam.brest.cources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

class getTotalCostTest {

    private static Map<Byte, BigDecimal> price;
    private static BigDecimal data;

    @BeforeAll
    public static void init(){
        price = new TreeMap<>();
        price.put((byte) 10, new BigDecimal("0.4"));
        price.put((byte) 20, new BigDecimal("0.3"));
        price.put((byte) 40, new BigDecimal("0.2"));
    }

    @Test
    void calcTotalCost1() {
        data = new getTotalCost().calcTotalCost(price, new BigDecimal("89"), new BigDecimal("10"));
        Assertions.assertEquals(data, new BigDecimal("196.0"));
    }

    @Test
    void calcTotalCost2() {
        data = new getTotalCost().calcTotalCost(price, new BigDecimal("90"), new BigDecimal("10"));
        Assertions.assertEquals(data, new BigDecimal("200.0"));
    }

    @Test
    void calcTotalCost3() {
        data = new getTotalCost().calcTotalCost(price, new BigDecimal("91"), new BigDecimal("10"));
        Assertions.assertEquals(data, new BigDecimal("193.0"));
    }

    @Test
    void calcTotalCost4() {
        data = new getTotalCost().calcTotalCost(price, new BigDecimal("21"), new BigDecimal("9"));
        Assertions.assertEquals(data, new BigDecimal("37.8"));
    }
}