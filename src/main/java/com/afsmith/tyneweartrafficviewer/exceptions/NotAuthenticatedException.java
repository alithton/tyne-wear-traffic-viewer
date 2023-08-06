package com.afsmith.tyneweartrafficviewer.exceptions;

/**
 * Exception thrown when a user attempts to access actions for which they are
 * not authenticated.
 */
public class NotAuthenticatedException extends Exception {
    public NotAuthenticatedException(String errorMessage) {
        super(errorMessage);
    }
}
