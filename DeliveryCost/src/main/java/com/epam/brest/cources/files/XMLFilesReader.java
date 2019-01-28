package com.epam.brest.cources.files;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class XMLFilesReader implements FilesReader {

    @Override
    public List<Map<Integer, BigDecimal>> readData(String[] filePaths) throws Exception {

        File inputFile = new File(FilesReader.getPatch(filePaths[0]));
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        saxParser.parse(inputFile, handler);
        return handler.getResult();
    }

    private static class XMLHandler extends DefaultHandler {
        private List<Map<Integer, BigDecimal>> price = new ArrayList<>();
        private Map<Integer, BigDecimal> priceWeight = new TreeMap<>();
        private Map<Integer, BigDecimal> priceDistance = new TreeMap<>();

        public List<Map<Integer, BigDecimal>> getResult() {
            price.add(priceWeight);
            price.add(priceDistance);
            return price;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atr) throws SAXException {

            if (qName.equalsIgnoreCase("cargo_weight")) {
                priceWeight.put(Integer.parseInt(data(atr)[0]), new BigDecimal(data(atr)[1]));
            } else if (qName.equalsIgnoreCase("cargo_distance")) {
                priceDistance.put(Integer.parseInt(data(atr)[0]), new BigDecimal(data(atr)[1]));
            }
        }

        private String[] data(Attributes atr) {
            String[] val = {atr.getValue("value"), atr.getValue("cost")};
            return  val;
        }
    }
}

