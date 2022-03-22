package com.example.ecommerce.exceptions;

import javax.security.sasl.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {
    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
