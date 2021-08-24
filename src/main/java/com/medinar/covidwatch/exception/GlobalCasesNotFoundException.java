package com.medinar.covidwatch.exception;

/**
 *
 * @author Rommel Medina
 */
public class GlobalCasesNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ContinentNotFoundException</code> without
     * detail message.
     */
    public GlobalCasesNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ContinentNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GlobalCasesNotFoundException(String msg) {
        super(msg);
    }
}
