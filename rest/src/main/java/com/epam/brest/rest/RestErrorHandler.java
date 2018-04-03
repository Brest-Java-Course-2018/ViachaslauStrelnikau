package com.epam.brest.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

@ControllerAdvice
public class RestErrorHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * HandleDataAccessException handle ParseException.
     * @param ex ParseException
     * @return exception mesage
     */
    @ExceptionHandler(ParseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String HandleParseException(ParseException ex) {
        LOGGER.debug("ParseException: {}", ex.getLocalizedMessage());
        return "ParseException:";
    }
}