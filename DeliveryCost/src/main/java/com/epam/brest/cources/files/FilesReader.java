package com.epam.brest.cources.files;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface FilesReader {

    List<Map<Integer, BigDecimal>> readData(final String[] filePaths) throws Exception;

    static String getPatch(String filePath) {
        String paths = Objects.requireNonNull(FilesReader.class.getClassLoader().getResource(filePath)).getPath();
        return paths;
    }

//    List<Map<String,Integer>> maps = new ArrayList<Map<String,Integer>>();
//...
//        maps.add(new HashMap<String,Integer>());
}
