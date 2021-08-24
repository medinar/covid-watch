package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.exception.ContinentalCasesNotFoundException;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Rommel Medina
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContinentalServiceImplIT {

    @Autowired
    ContinentalService service;

    public ContinentalServiceImplIT() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTotal method, of class ContinentalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTotalHappyPath() throws Exception {
        System.out.println("getTotal");
        String continent = "Asia";
        Optional<ContinentalTotal> result = service
                .getTotal(continent, false, false, false, false);
        assertNotNull(result.orElse(null));
        assertEquals(continent, result.get().getContinent());
    }

    /**
     * Test of getTotal method, of class ContinentalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testContinentalCasesNotFoundException() throws Exception {
        System.out.println("getContinentalTotal");
        String continent = "Pacific Ocean";
        String expectedMessage = "Continent not found: " + continent;

        Exception exception = assertThrows(ContinentalCasesNotFoundException.class, () -> {
            service.getTotal(continent, false, false, true, false);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test of getTotals method, of class ContinentalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTotalsHappyPath() throws Exception {
        System.out.println("getTotals");
        List<ContinentalTotal> result = service
                .getTotals(false, false, false, false);
        assertNotNull(result.get(0));
        assertFalse(result.isEmpty());
    }

}
