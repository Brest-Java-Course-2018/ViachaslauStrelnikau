package com.epam.brest.web_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    @ResponseStatus(HttpStatus.OK)
    public final String defaultPageRedirect() {
        return "redirect:groups";
    }
}
