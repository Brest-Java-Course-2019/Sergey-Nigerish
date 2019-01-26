
package com.epam.brest.cources;

import com.epam.brest.cources.files.FileReader;
import com.epam.brest.cources.files.XMLFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DeliveryCost {
    private static BigDecimal weight;
    private static BigDecimal distance;
    private static Map<Byte, BigDecimal> price = new TreeMap<>();

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileReader fileReader = new XMLFileReader();
        String[] file = {"Price.xml"};
        List<Map<Integer, BigDecimal>> prices = fileReader.readData(file);
//        System.out.println(prices.get(1));


//        parse XML prise from "Cost.xml"
//        try {
//            File inputFile = new File(FilePatch.getPatch("Cost.xml"));
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser saxParser = factory.newSAXParser();
//            CostParser costHandler = new CostParser();
//            saxParser.parse(inputFile, costHandler);
//            price = costHandler.price;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        parse XML prise from "Cost.xml"

        try {
            System.out.printf("Enter the weight of cargo (t): ");
            weight = new BigDecimal(reader.readLine());
            System.out.printf("Enter the distance (km): ");
            distance = new BigDecimal(reader.readLine());
            LOGGER.debug("Data item: {} {}", distance, weight);
            System.out.printf("Shipping cost: %.2f$", new getTotalCost().calcTotalCost(prices.get(0), weight, distance));
            LOGGER.debug("Shipping cost: {} {}", new getTotalCost().calcTotalCost(prices.get(0), weight, distance));
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error in input! Please restart.");
        }
    }

}


