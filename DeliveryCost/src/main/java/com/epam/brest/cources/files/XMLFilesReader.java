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
    public List<Map<Integer, BigDecimal>> readData(String[] fileNames) throws Exception {

        File inputFile = new File(FilesReader.getFullPath(fileNames[0]));
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler();
        saxParser.parse(inputFile, xmlHandler);
        return xmlHandler.getPriceFromXML();
    }

    private static class XMLHandler extends DefaultHandler {
        private static final String[] LAYOUT = {"cargo_weight", "cargo_distance", "value", "cost"};
        private List<Map<Integer, BigDecimal>> price = new ArrayList<>();
        private Map<Integer, BigDecimal> priceWeight = new TreeMap<>();
        private Map<Integer, BigDecimal> priceDistance = new TreeMap<>();

        public List<Map<Integer, BigDecimal>> getPriceFromXML() {
            price.add(priceWeight);
            price.add(priceDistance);
            return price;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atr) throws SAXException {

            if (qName.equalsIgnoreCase(LAYOUT[0])) {
                priceWeight.put(Integer.parseInt(getValues(atr)[0]), new BigDecimal(getValues(atr)[1]));
            } else if (qName.equalsIgnoreCase(LAYOUT[1])) {
                priceDistance.put(Integer.parseInt(getValues(atr)[0]), new BigDecimal(getValues(atr)[1]));
            }
        }

        private String[] getValues(Attributes attributes) {
            String[] val = {attributes.getValue(LAYOUT[2]), attributes.getValue(LAYOUT[3])};
            return  val;
        }
    }
}

