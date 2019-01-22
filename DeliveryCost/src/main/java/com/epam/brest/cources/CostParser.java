package com.epam.brest.cources;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;


public class CostParser extends DefaultHandler {

    private boolean bCost = false;
    static Map<Byte, BigDecimal> price = new TreeMap<>();
    private Byte bWeight;

//    @Override
//    public void endDocument() throws SAXException {
//        new DeliveryCost().setPrice(price);
//        System.out.println(price);
//
//    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("сargo")) {
            String weight = attributes.getValue("weight");
            bWeight = Byte.parseByte(weight);
//            System.out.println("Weight : " + weight);
        } else if (qName.equalsIgnoreCase("cost")) {
            bCost = true;
        } 
    }

//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if (qName.equalsIgnoreCase("сargo")) {
//            System.out.println("End Element :" + qName);
//        }
//    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bCost) {
            price.put(bWeight, new BigDecimal(new String(ch, start, length)));
//            System.out.println("Cost: " + new String(ch, start, length));
            bCost = false;
        }
    }
}