package com.epam.brest.cources;

import com.epam.brest.cources.calculation.СalculationImpl;
import com.epam.brest.cources.files.FilesReader;
import com.epam.brest.cources.files.XMLFilesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class CalculationImplTest {

    private static List<Map<Integer, BigDecimal>> prices = new ArrayList<>();
    private static Map<Integer, BigDecimal> priceWeight = new TreeMap<>();
    private static Map<Integer, BigDecimal> priceDistance = new TreeMap<>();
    private static BigDecimal[] values = new BigDecimal[2];
    private static BigDecimal data;

    @BeforeAll
    public static void init(){
        priceWeight.put(10, new BigDecimal("0.4"));
        priceWeight.put(20, new BigDecimal("0.3"));
        priceWeight.put(40, new BigDecimal("0.2"));
        priceDistance.put(100, new BigDecimal("1"));
        priceDistance.put(300, new BigDecimal("0.8"));
        priceDistance.put(1000, new BigDecimal("0.6"));
        prices.add(priceWeight);
        prices.add(priceDistance);
    }

    @Test
    void calcTotalCost1() {
        values[0] = new BigDecimal("89");
        values[1] = new BigDecimal("10");
        data = new СalculationImpl().calculate(prices, values);
        Assertions.assertEquals(data, new BigDecimal("196.0"));
    }

    @Test
    void calcTotalCost2() {
        values[0] = new BigDecimal("90");
        values[1] = new BigDecimal("10");
        data = new СalculationImpl().calculate(prices, values);
        Assertions.assertEquals(data, new BigDecimal("200.0"));
    }

    @Test
    void calcTotalCost3() {
        values[0] = new BigDecimal("91");
        values[1] = new BigDecimal("10");
        data = new СalculationImpl().calculate(prices, values);
        Assertions.assertEquals(data, new BigDecimal("193.0"));
    }

    @Test
    void calcTotalCost4() {
        values[0] = new BigDecimal("21");
        values[1] = new BigDecimal("9");
        data = new СalculationImpl().calculate(prices, values);
        Assertions.assertEquals(data, new BigDecimal("37.8"));
    }

    @Test
    void XMLFilesReaderTest() throws Exception {
        FilesReader filesReader = new XMLFilesReader();
        String[] file = {"Price.xml"};
        List<Map<Integer, BigDecimal>> testPrices = filesReader.readData(file);
        Assertions.assertEquals(testPrices, prices);
    }
}