package com.epam.brest.cources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilePatchTest {

    @Test
    void getPatchTest() {
        //write something?
        String filePatch = FilePatch.getPatch("Cost.xml");
        Assertions.assertTrue(filePatch.endsWith("target/classes/Cost.xml"));
    }
}