package com.epam.brest.web_app.controllers;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorController extends RuntimeException {

    /**
     * method handleException handle app exceptions
     *
     * @param model attributes map
     * @param ex Exception
     * @return template name
     */
    @ExceptionHandler(Exception.class)
    public String handleException( Exception  ex,Model model) {
        model.addAttribute  ("Title","Exception occured");
        model.addAttribute  ("Text",ex.toString());
        return "exception";
    }


//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFound.class)
//    public String ErrorPage(Model model) {
//
//                String errorMsg = "Http Error Code: 404. Resource not found";
//                model.addAttribute  ("Title","404");
//                model.addAttribute  ("Text",errorMsg);
//
//               return "exception";
//    }

}

