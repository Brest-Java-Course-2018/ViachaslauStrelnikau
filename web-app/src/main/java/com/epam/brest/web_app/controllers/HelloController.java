package com.epam.brest.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HelloController class.
 * Designed to test
 */
@Controller
public class HelloController {

    @GetMapping(value = "/*")
    public String defaultPageRedirect() {
        return "redirect:departments";
    }

    @GetMapping(value = "/hello")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                        final Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}

