package com.epam.brest.project.ps.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Start {
    /**
     * Redirect to default page -> departments.
     *
     * @return redirect path
     */
    @GetMapping(value = "/stat")
    public String defaultPageRedirect() {
        return "redirect:start";
    }
}
