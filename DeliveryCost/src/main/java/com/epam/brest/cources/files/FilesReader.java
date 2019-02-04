package com.epam.brest.cources.files;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * FilesReader interface for reading price lists.
 *
 * @author  Sergey Nigerish
 * @version 1.0
 * @since   2019-02-01
 * */
public interface FilesReader {

    /**
     * This is the main method which reads price lists into variables.
     *
     * @param fileNames an array of price list names from which to read the data.
     * @throws Exception if an I/O error
     * @return list of read data arrays.
     */
    List<Map<Integer, BigDecimal>> readData(final String[] fileNames) throws Exception;

    /**
     * The method returns the full path to the file.
     *
     * @param fileName the name of the file for which you need to get the full path.
     * @return full file path.
     */
    static String getFullPath(String fileName) {
        return Objects.requireNonNull(FilesReader.class.getClassLoader().getResource(fileName)).getPath();
    }
}
