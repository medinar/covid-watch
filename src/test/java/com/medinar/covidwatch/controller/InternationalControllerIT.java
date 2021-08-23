package com.medinar.covidwatch.controller;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

/**
 *
 * @author Rommel Medina
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InternationalControllerIT {

    @Autowired
    InternationalController controller;
    
    @Mock
    Model model;
    
    public InternationalControllerIT() {
    }

    /**
     * Test of getTotal method, of class InternationalController.
     */
    @Test
    public void testGetInternationalTotal() throws Exception {
        System.out.println("getTotal");
        String country = "Canada";
        String expResult = "international";
        String result = controller.getTotal(
                model, country, false, false, true, false
        );
        
        assertEquals(expResult, result);
    }
    
}
