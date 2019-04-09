package com.epam.brest.project.ps.rest_app;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for version.
 */
@RestController
public class VersionController {

    /**
     * Application version.
     */
    private static final String VERSION = "1.0.0";

    /**
     * Version.
     *
     * @return app version.
     */
    @GetMapping(value = "/version")
    public final String version() {
        return VERSION;
    }
}
