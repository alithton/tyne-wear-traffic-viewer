package com.afsmith.tyneweartrafficviewer.exceptions;

/**
 * Exception thrown when attempting to add a user that already exists.
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
