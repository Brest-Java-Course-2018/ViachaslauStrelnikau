package com.epam.brest.web_app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home MVC controller.
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Redirect to default page -> departments.
     *
     * @return redirect path
     */
    @GetMapping(value = "/*")
    public final String defaultPageRedirect( ) {
        LOGGER.debug("HomeController - redirect");
        return "redirect:/groups";
    }
}
