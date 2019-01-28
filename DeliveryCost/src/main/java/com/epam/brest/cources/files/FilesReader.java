package com.epam.brest.cources.files;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface FilesReader {

    List<Map<Integer, BigDecimal>> readData(final String[] fileNames) throws Exception;

    static String getFullPath(String fileName) {
        String paths = Objects.requireNonNull(FilesReader.class.getClassLoader().getResource(fileName)).getPath();
        return paths;
    }
}
