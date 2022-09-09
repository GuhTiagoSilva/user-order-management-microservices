package com.gustavo.orderapi.clients.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.orderapi.controllers.exceptions.StandardError;
import com.gustavo.orderapi.services.exceptions.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class UserClientResourceNotFoundExceptionHandler implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        StandardError standardError = getStandardErrorFromResponse(response);

        switch (response.status()) {
            case 404:
                throw new ResourceNotFoundException(standardError.getMessage());
            default:
                return errorDecoder.decode(s, response);
        }
    }

    public StandardError getStandardErrorFromResponse(Response response) {
        StandardError standardError;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            standardError = mapper.readValue(bodyIs, StandardError.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return standardError;
    }
}
