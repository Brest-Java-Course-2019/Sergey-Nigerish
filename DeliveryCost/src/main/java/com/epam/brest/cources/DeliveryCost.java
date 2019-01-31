
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
import java.util.List;
import java.util.Map;

public class DeliveryCost {
    private static final String[] MESSAGES = {"$", "Enter the", "weight of cargo (t)", "distance (km)", "Data input",
            "Shipping cost", "Error in input! Please restart."};
    private static final BigDecimal[] INPUT_VALUES = new BigDecimal[2];
    private static final Logger LOGGER = LogManager.getLogger();
    private static String[] file = {"Price.xml"};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

//        if (args.length != 0) {
//            file = new String[args.length];
//            int i = 0;
//            for (String arg : args) {
//                file[i++] = arg;
//            }
//        }

        FilesReader filesReader = new XMLFilesReader();
        List<Map<Integer, BigDecimal>> prices = filesReader.readData(file);
        System.out.println(prices.get(0));


        try {
            System.out.printf("%s %s: ", MESSAGES[1], MESSAGES[2]);
            INPUT_VALUES[0] = new BigDecimal(reader.readLine());
            System.out.printf("%s %s: ", MESSAGES[1], MESSAGES[3]);
            INPUT_VALUES[1] = new BigDecimal(reader.readLine());
            BigDecimal cost = new CalculationImpl().calculateCost(prices, INPUT_VALUES);
            LOGGER.debug("{}: {} {}", MESSAGES[4], INPUT_VALUES[0], INPUT_VALUES[1]);
            System.out.printf("%s: %.2f%s%n", MESSAGES[5], cost, MESSAGES[0]);
            LOGGER.debug("{}: {}{}", MESSAGES[5], String.format("%.2f", cost), MESSAGES[0]);
        } catch (IOException | NumberFormatException e) {
            System.out.println(MESSAGES[6]);
        }


    }

}


