package com.afsmith.tyneweartrafficviewer.exceptions;

/**
 * Exception thrown when attempting to save invalid traffic data.
 */
public class InvalidTrafficDataException extends Exception {
    public InvalidTrafficDataException(String errorMessage) {
        super(errorMessage);
    }
}
