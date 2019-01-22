
package com.epam.brest.cources;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class DeliveryCost {
    private static BigDecimal weight;
    private static BigDecimal distance;
    private static Map<Byte, BigDecimal> price = new TreeMap<>();

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        parse XML prise from "Cost.xml"
        try {
            File inputFile = new File("Cost.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CostParser costHandler = new CostParser();
            saxParser.parse(inputFile, costHandler);
            price = costHandler.price;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        parse XML prise from "Cost.xml"

        try {
            System.out.printf("Enter the weight of cargo (t): ");
            weight = new BigDecimal(reader.readLine());
            System.out.printf("Enter the distance (km): ");
            distance = new BigDecimal(reader.readLine());
            System.out.println("Shipping cost: " + new getTotalCost().calcTotalCost(price, weight, distance) + "$");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error in input! Please restart.");
        }
    }

}


