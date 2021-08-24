package com.medinar.covidwatch.exception;

/**
 *
 * @author Rommel Medina
 */
public class InternationalCasesNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>InternationalCasesNotFoundException</code> without
     * detail message.
     */
    public InternationalCasesNotFoundException() {
    }

    /**
     * Constructs an instance of <code>InternationalCasesNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InternationalCasesNotFoundException(String msg) {
        super(msg);
    }
}
