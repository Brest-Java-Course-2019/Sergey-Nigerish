package com.epam.brest.cources;

import com.epam.brest.cources.files.FilesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilePatchTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void getPatchTest() {
        String filePatch = FilesReader.getFullPath("Price.xml");
        LOGGER.debug("Test full filePatch, result: {}", filePatch);
        Assertions.assertTrue(filePatch.endsWith("/Price.xml"));
    }
}