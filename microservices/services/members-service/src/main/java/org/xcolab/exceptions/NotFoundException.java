package org.xcolab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Object Not Found")
public class NotFoundException extends Exception {

    public NotFoundException(String msg) {
        super(msg);
    }
}
