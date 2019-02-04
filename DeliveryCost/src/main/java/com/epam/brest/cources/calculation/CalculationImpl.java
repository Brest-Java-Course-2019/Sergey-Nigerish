package com.epam.brest.cources.calculation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * CalculationImpl this is implementation Calculation interface to calculate the
 * cost of shipping.
 *
 * The cost of transportation is calculated:
 * 1. On the distance depends on the <tt>discount rate by distance</tt>,
 *      which is taken from the price.
 * 2. From the price is taken the maximum capacity and the load is divided by cars.
 *
 * Simplified formula:
 * <tt>Total shipping cost</tt> = <tt>distance</tt>
 *                                  * <tt>discount rate by distance</tt>
 *                                  * <tt>weight</tt>
 *                                  * <tt>discount rate by weight</tt>
 *
 * @author  Sergey Nigerish
 * @version 1.0
 * @since   2019-02-01
 * */
public class CalculationImpl implements Calculation {

    /**
     * @param totalCost variable in which the total cost of the freight is recorded.
     */
    private BigDecimal totalCost = BigDecimal.ZERO;

    /**
     * @param distance distance shipping.
     */
    private BigDecimal distance;

    /**
     * @param distanceRatio discount rate by distance.
     */
    private BigDecimal distanceRatio;

    /**
     * @param LOGGER process log variable.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public BigDecimal calculateCost(final List<Map<Integer, BigDecimal>> price,
                                    final BigDecimal[] inputValues) {
        LOGGER.debug("calculateCost input price: {}", price);
        LOGGER.debug("calculateCost input inputValues: {}", inputValues);
        distanceRatio = calculateRatioAndCost(price.get(1), inputValues[1]);
        LOGGER.debug("calculateCost distanceRatio: {}", distanceRatio);
        distance = inputValues[1];
        totalCost = calculateRatioAndCost(price.get(0), inputValues[0], true);
        return totalCost;
    }

    /**
     * calculateRatioAndCost - overloading methods.
     * The method returns the ratio of the price to the values in the price.
     * list and (or) calculates the total cost of freight transportation.
     *
     * @param priceList the price in which the match will be searched.
     * @param searchValue the value that will be searched in the price list.
     * @param cost if <tt>false</tt> calculates only the ratio in the price,
     *             if <tt>true</tt> calculates the ratio in the price and the cost of shipping.
     * @return ratio or shipping cost.
     */
    private BigDecimal calculateRatioAndCost(
                    @javax.annotation.Nonnull final Map<Integer, BigDecimal> priceList,
                                                BigDecimal searchValue,
                                                final boolean cost) {
        LOGGER.debug("calculateRatioAndCost input priceList: {}", priceList);
        LOGGER.debug("calculateRatioAndCost input searchValue: {}", searchValue);
        LOGGER.debug("calculateRatioAndCost input cost: {}", cost);
        SortedSet<Integer> sortedKeys = new TreeSet<>(priceList.keySet());
        Integer valueMin = sortedKeys.first();

        if (cost) {
            Integer valueMax = sortedKeys.last();
            BigDecimal decimalValueMax = BigDecimal.valueOf(valueMax);
            while (searchValue.doubleValue() > valueMax) {
                totalCost = totalCost.add(multiplyFour(decimalValueMax,
                                                        priceList.get(valueMax),
                                                        distance,
                                                        distanceRatio));
                searchValue = searchValue.subtract(decimalValueMax);
            }
        }

        for (Integer key : sortedKeys) {
            if (valueMin >= searchValue.doubleValue()) {
                break;
            }
            valueMin = key;
        }

        if (cost) {
            totalCost = totalCost.add(multiplyFour(searchValue,
                                                    priceList.get(valueMin),
                                                    distance,
                                                    distanceRatio));
            return totalCost;
        } else {
            return priceList.get(valueMin);
        }
    }

    /**
     * Overloading methods
     * If the last parameter is not specified, then run overloading methods
     * and set last parameter <tt>false</tt>.
     *
     * @param priceList price list.
     * @param searchValue search value in price list.
     * @return <tt>discount rate by distance</tt>.
     */
    private BigDecimal calculateRatioAndCost(final Map<Integer, BigDecimal> priceList,
                                             final BigDecimal searchValue) {
        return calculateRatioAndCost(priceList, searchValue, false);
    }

    /**
     * @return Multiply four BigDecimal numbers:
     * @param oneNumber number;
     * @param twoNumber number;
     * @param threeNumber number;
     * @param fourNumber number.
     */
    private BigDecimal multiplyFour(final BigDecimal oneNumber,
                                    final BigDecimal twoNumber,
                                    final BigDecimal threeNumber,
                                    final BigDecimal fourNumber) {
        BigDecimal result = oneNumber.multiply(twoNumber);
        result = result.multiply(threeNumber);
        result = result.multiply(fourNumber);
        LOGGER.debug("multiplyFour: {}*{}*{}*{}={}", oneNumber, twoNumber,
                                            threeNumber, fourNumber, result);
        return result;
    }
}
