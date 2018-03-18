package com.epam.brest.web_app.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public String ErrorPage(HttpServletRequest httpRequest, Model model) {

        ModelAndView errorPage = new ModelAndView("errorPage");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                model.addAttribute  ("Title","400");
                model.addAttribute  ("Text",errorMsg);
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                model.addAttribute  ("Title","401");
                model.addAttribute  ("Text",errorMsg);
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                model.addAttribute  ("Title","404");
                model.addAttribute  ("Text",errorMsg);
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                model.addAttribute  ("Title","500");
                model.addAttribute  ("Text",errorMsg);
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return "exception";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }


}

