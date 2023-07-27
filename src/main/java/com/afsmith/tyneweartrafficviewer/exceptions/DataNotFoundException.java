package com.afsmith.tyneweartrafficviewer.exceptions;

/**
 * Exception thrown when requested data could not be found.
 */
public class DataNotFoundException extends Exception {

    public DataNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
