package com.epam.brest.cources;

import com.epam.brest.cources.files.FilesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilePatchTest {

    @Test
    void getPatchTest() {
        String filePatch = FilesReader.getFullPath("Price.xml");
        System.out.println(filePatch);
        Assertions.assertTrue(filePatch.endsWith("/Price.xml"));
    }
}