package com.epam.brest.mongobackend.errors;

public class NotFoundInDBException extends StudentMgrException {
    public NotFoundInDBException(String s) {
        super(s);
    }
}
