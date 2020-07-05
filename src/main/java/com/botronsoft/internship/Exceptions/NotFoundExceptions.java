package com.botronsoft.internship.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundExceptions extends RuntimeException{
    public NotFoundExceptions() {
        super();
    }

    public NotFoundExceptions(String message) {
        super(message);
    }

}
