package com.medinar.covidwatch.controller;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.ui.Model;

/**
 *
 * @author Rommel Medina
 */
public class InternationalControllerIT {
    
    public InternationalControllerIT() {
    }

    /**
     * Test of getTotal method, of class InternationalController.
     */
    @Test
    public void testGetTotal() throws Exception {
        System.out.println("getTotal");
        Model model = null;
        String country = "";
        boolean yesterday = false;
        boolean twoDaysAgo = false;
        boolean strict = false;
        boolean allowNull = false;
        InternationalController instance = new InternationalController();
        String expResult = "";
        String result = instance.getTotal(model, country, yesterday, twoDaysAgo, strict, allowNull);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
