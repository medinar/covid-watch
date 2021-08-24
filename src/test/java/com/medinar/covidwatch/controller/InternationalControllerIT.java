package com.medinar.covidwatch.controller;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

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
     *
     * @throws java.lang.Exception
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

    /**
     * Test of getTotal method, of class InternationalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @org.junit.jupiter.api.Test
    public void testResponseStatusException() throws Exception {
        System.out.println("getInternationalTotal");
        String country = "Manila";
        String expectedMessage = "Country not found: " + country;

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            controller.getTotal(model, country, false, false, true, false);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
