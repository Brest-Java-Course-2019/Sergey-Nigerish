
package com.epam.brest.cources;

import com.epam.brest.cources.calculation.CalculationImpl;
import com.epam.brest.cources.files.FilesReader;
import com.epam.brest.cources.files.XMLFilesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * <h1>DeliveryCost</h1>
 * The DeliveryCost program based on the data entered and the
 * price list calculates the cost of shipping.
 *
 * @author  Sergey Nigerish
 * @version 1.0
 * @since   2019-02-01
 * */
final class DeliveryCost {

    /**
     * @param MESSAGES array of output messages.
     */
    private static final String[] MESSAGES = {"$",                                      //0
                                                "Enter the",                            //1
                                                "weight of cargo (t)",                  //2
                                                "distance (km)",                        //3
                                                "Data input",                           //4
                                                "Shipping cost",                        //5
                                                "Error in input! Please restart."};     //6

    /**
     * @param INPUT_VALUES array in which the input data is stored.
     */
    private static final BigDecimal[] INPUT_VALUES = new BigDecimal[2];

    /**
     * @param LOGGER process log variable.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param FILES price list file names array.
     */
    private static final String[] FILES = {"Price.xml"};

    /**
     * The application's entry point.
     *
     * @param args not used
     */
    public static void main(final String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(
                                        new InputStreamReader(System.in, StandardCharsets.UTF_8));

        FilesReader filesReader = new XMLFilesReader();
        List<Map<Integer, BigDecimal>> prices = filesReader.readData(FILES);
        LOGGER.debug("Price from file: {}", FILES, prices);

        try {
            System.out.printf("%s %s: ", MESSAGES[1], MESSAGES[2]);
            INPUT_VALUES[0] = new BigDecimal(bufferedReader.readLine());
            System.out.printf("%s %s: ", MESSAGES[1], MESSAGES[3]);
            INPUT_VALUES[1] = new BigDecimal(bufferedReader.readLine());
            BigDecimal cost = new CalculationImpl().calculateCost(prices, INPUT_VALUES);
            LOGGER.debug("{}: {} {}", MESSAGES[4], INPUT_VALUES[0], INPUT_VALUES[1]);
            System.out.printf("%s: %.2f%s%n", MESSAGES[5], cost, MESSAGES[0]);
            LOGGER.debug("{}: {}{}", MESSAGES[5], String.format("%.2f", cost), MESSAGES[0]);
        } catch (IOException | NumberFormatException e) {
            LOGGER.error(MESSAGES[6]);
            System.out.println(MESSAGES[6]);
        }
    }
}


