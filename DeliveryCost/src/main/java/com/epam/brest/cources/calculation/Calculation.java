package com.epam.brest.cources.calculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Calculation interface to calculate the cost of shipping
 * price list calculates the cost of shipping.
 *
 * @author  Sergey Nigerish
 * @version 1.0
 * @since   2019-02-01
 * */
interface Calculation {

    /**
     * This is the main method which calculates the cost of shipping.
     *
     * @param price list of prices in which matches will be inputValues.
     * @param inputValues the entered data will be compared with the price list.
     * @return calculates the cost of shipping by input data.
     */
    BigDecimal calculateCost(final List<Map<Integer, BigDecimal>> price,
                             final BigDecimal[] inputValues);
}
