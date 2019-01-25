package com.epam.brest.cources;

import java.util.Objects;

public class FilePatch {
    public static String getPatch(String filePath) {
        String paths = Objects.requireNonNull(FilePatch.class.getClassLoader().getResource(filePath)).getPath();
        return paths;
    }
}
