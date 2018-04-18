package com.epam.brest.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home MVC controller.
 */
@Controller
public class HomeController {
    /**
     * Redirect to default page -> departments.
     *
     * @return redirect path
     */
    @GetMapping(value = "/*")
    public final String defaultPageRedirect() {
        return "redirect:groups";
    }
}
