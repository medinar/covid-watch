package com.medinar.covidwatch.exception;

/**
 *
 * @author Rommel Medina
 */
public class CountryNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CountryNotFoundException</code> without
     * detail message.
     */
    public CountryNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CountryNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CountryNotFoundException(String msg) {
        super(msg);
    }
}
