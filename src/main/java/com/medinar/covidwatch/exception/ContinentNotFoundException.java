package com.medinar.covidwatch.exception;

/**
 *
 * @author Rommel Medina
 */
public class ContinentNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ContinentNotFoundException</code> without
     * detail message.
     */
    public ContinentNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ContinentNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ContinentNotFoundException(String msg) {
        super(msg);
    }
}
