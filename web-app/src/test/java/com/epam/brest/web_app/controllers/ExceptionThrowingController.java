package com.epam.brest.web_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@Controller
public class ExceptionThrowingController {
    @GetMapping(value = "/exceptionTrow")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String  find() throws Exception {
        throw new RestClientException("global_error_test");
    }
}
