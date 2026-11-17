
package com.healthhub.healthhub;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionHandlerTest {

    private final ApiExceptionHandler handler =
            new ApiExceptionHandler();

    @Test
    void malformedRequestShouldReturnBadRequest() {

        HttpMessageNotReadableException exception =
                new HttpMessageNotReadableException(
                        "Invalid JSON request body",
                        (org.springframework.http.HttpInputMessage) null
                );


        ResponseEntity<Map<String, String>> response =
                handler.handleMalformedRequest(exception);

        assertEquals(HttpStatus.BAD_REQUEST,
                response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(
                "Invalid request body. Check JSON syntax and field types.",
                response.getBody().get("error")
        );
    }
}
