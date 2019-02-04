package com.epam.brest.cources.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * CalculationImpl this is implementation FilesReader to read data from XML file.
 *
 * @author  Sergey Nigerish
 * @version 1.0
 * @since   2019-02-01
 * */
public class XMLFilesReader implements FilesReader {

    /**
     * @param LOGGER process log variable.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Map<Integer, BigDecimal>> readData(String[] fileNames) throws Exception {


        LOGGER.debug("XML file for read: {}", fileNames);
        File inputFile = new File(FilesReader.getFullPath(fileNames[0]));
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler();
        saxParser.parse(inputFile, xmlHandler);
        return xmlHandler.getPriceFromXML();
    }

    private static class XMLHandler extends DefaultHandler {

        /**
         * @param LAYOUT array for parsing XML file.
         */
        private static final String[] LAYOUT = {"cargo_weight",         //0
                                                "cargo_distance",       //1
                                                "value",                //2
                                                "cost"};                //3

        /**
         * @param priceList the list of price lists.
         */
        private final List<Map<Integer, BigDecimal>> priceList = new ArrayList<>();

        /**
         * @param priceWeight price list for weight.
         */
        private final Map<Integer, BigDecimal> priceWeight = new TreeMap<>();

        /**
         * @param priceDistance price list for distance.
         */
        private final Map<Integer, BigDecimal> priceDistance = new TreeMap<>();

        /**
         * Adds the received price lists to the list of price lists.
         *
         * @return list of read data arrays.
         */
        List<Map<Integer, BigDecimal>> getPriceFromXML() {
            priceList.add(priceWeight);
            priceList.add(priceDistance);
            return priceList;
        }

        @Override
        public void startElement(final String uri,
                                 final String localName,
                                 final String qName,
                                 final Attributes atr) {

            if (qName.equalsIgnoreCase(LAYOUT[0])) {
                priceWeight.put(Integer.parseInt(getValues(atr)[0]),
                                    new BigDecimal(getValues(atr)[1]));
            } else if (qName.equalsIgnoreCase(LAYOUT[1])) {
                priceDistance.put(Integer.parseInt(getValues(atr)[0]),
                                    new BigDecimal(getValues(atr)[1]));
            }
        }

        /**
         * Receive an array of values from necessary attributes.
         *
         * @param attributes attributes from XML file.
         * @return array of values from necessary attributes.
         */
        private String[] getValues(final Attributes attributes) {
            return new String[]{attributes.getValue(LAYOUT[2]),
                                attributes.getValue(LAYOUT[3])};
        }
    }
}

