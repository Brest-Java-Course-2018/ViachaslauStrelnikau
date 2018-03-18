package com.epam.brest.web_app.controllers;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ErrorController extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public String handleResourceNotFoundException( Exception  ex,Model model) {
        model.addAttribute  ("Title","Exception occured");
        model.addAttribute  ("Text",ex.toString());
        return "exception";
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public String ErrorPage(Model model) {

                String errorMsg = "Http Error Code: 404. Resource not found";
                model.addAttribute  ("Title","404");
                model.addAttribute  ("Text",errorMsg);

               return "exception";
    }




}

