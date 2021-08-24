package com.medinar.covidwatch.exception;

/**
 *
 * @author Rommel Medina
 */
public class ContinentalCasesNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ContinentalCasesNotFoundException</code> without
     * detail message.
     */
    public ContinentalCasesNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ContinentalCasesNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ContinentalCasesNotFoundException(String msg) {
        super(msg);
    }
}
