package com.epam.brest.project.ps.rest_app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class VersionController {

    private final static String VERSION = "1.0.0";

    /**
     * Version.
     *
     * @return app version
     */
    @GetMapping(value = "/version")
    public String version() {
        return VERSION;
    }
}
