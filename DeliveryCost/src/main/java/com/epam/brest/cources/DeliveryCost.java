
package com.epam.brest.cources;

import com.epam.brest.cources.calculation.СalculationImpl;
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
    private static BigDecimal[] data = new BigDecimal[2];

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FilesReader filesReader = new XMLFilesReader();
        String[] file = {"Price.xml"};
        List<Map<Integer, BigDecimal>> prices = filesReader.readData(file);


        try {
            System.out.printf("Enter the weight of cargo (t): ");
            data[0] = new BigDecimal(reader.readLine());
            System.out.printf("Enter the distance (km): ");
            data[1] = new BigDecimal(reader.readLine());
            BigDecimal cost = new СalculationImpl().calculate(prices, data);
            LOGGER.debug("Data item: {} {}", data[0], data[1]);
            System.out.printf("Shipping cost: %.2f$\n", cost);
            LOGGER.debug("Shipping cost: {}$", String.format("%.2f", cost));
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error in input! Please restart.");
        }
    }

}


