package com.manager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response extends ResponseEntity<String> {
    private static final String  	RESULT_STR = "{ \"result\":\"@\" }";

    public Response(String result, HttpStatus status){
        super(RESULT_STR.replace("@", result.replace("\"", "\\\"")), status);
    }
}
