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
public class GlobalControllerIT {

    @Autowired
    GlobalController controller;
    
    @Mock
    Model model;

    public GlobalControllerIT() {
    }

    /**
     * Test of getTotal method, of class GlobalController.
     */
    @Test
    public void testGetGlobalTotalHappyPath() throws Exception {
        System.out.println("getTotal");
        String expResult = "index";
        
        String result = controller.getTotal(model, false, false, false);
        
        assertEquals(expResult, result);
    }

}
