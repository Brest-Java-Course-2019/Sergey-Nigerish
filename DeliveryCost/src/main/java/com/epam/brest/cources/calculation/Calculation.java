package com.epam.brest.cources.calculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Calculation {
    BigDecimal calculateCost(final List<Map<Integer, BigDecimal>> price, final BigDecimal[] inputValues);
}
