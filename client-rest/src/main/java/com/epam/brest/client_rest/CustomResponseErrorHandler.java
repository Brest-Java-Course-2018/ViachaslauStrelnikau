package com.epam.brest.client_rest;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {
    ResponseErrorHandler responseErrorHandler= new DefaultResponseErrorHandler();
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return responseErrorHandler.hasError(clientHttpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        throw new ServerDataAccessException("Something goes wrong with data! "
                +" "+clientHttpResponse.getStatusText()
                +" "+clientHttpResponse.getBody() );
    }
}
