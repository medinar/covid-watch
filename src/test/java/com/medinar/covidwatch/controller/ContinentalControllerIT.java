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
public class ContinentalControllerIT {
    
    @Autowired
    ContinentalController controller;
    
    @Mock
    Model model;
    
    public ContinentalControllerIT() {
    }

    /**
     * Test of getContinentalTotal method, of class ContinentalController.
     */
    @Test
    public void testGetContinentalTotalHappyPath() throws Exception {
        System.out.println("getContinentalTotal");
        String continent = "South America";

        String expResult = "continental";
        String result = controller.getContinentalTotal(
                model, continent, false, false, true, false
        );
        assertEquals(expResult, result);
    }
    
}
