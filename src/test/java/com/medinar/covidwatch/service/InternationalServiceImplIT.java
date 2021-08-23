package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.InternationalTotal;
import java.util.List;
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
public class InternationalServiceImplIT {

    @Autowired
    InternationalService service;

    public InternationalServiceImplIT() {
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
     * Test of getTotals method, of class InternationalServiceImpl.
     */
    @Test
    public void testGetTotalsHappyPath() throws Exception {
        System.out.println("getTotals");
        List<InternationalTotal> result = service.getTotals(false, false, "", false);
        assertNotNull(result.get(0));
    }

    /**
     * Test of getTotal method, of class InternationalServiceImpl.
     */
    @Test
    public void testGetTotalHappyPath() throws Exception {
        System.out.println("getTotal");
        String country = "Philippines";
        InternationalTotal result = service.getTotal(country, false, false, true, false);
        assertNotNull(result);
        assertEquals(country, result.getCountry());
    }
    
    /**
     * Test of getTotal method, of class InternationalServiceImpl.
     */
    @Test
    public void testGetTotalUnhappyPath() throws Exception {
        System.out.println("getTotal");
        String country = "Not-country";
        InternationalTotal result = service.getTotal(country, false, false, true, false);
        assertNull(result);
    }

    /**
     * Test of getTotalsbyContinent method, of class InternationalServiceImpl.
     */
    @Test
    public void testGetTotalsbyContinentHappyPath() throws Exception {
        System.out.println("getTotalsbyContinent");
        String continent = "Asia";
        List<InternationalTotal> result = service
                .getTotalsbyContinent(continent, false, false, true, false);
        assertNotNull(result.get(0));
        assertEquals(continent, result
                .stream().filter(total -> total.getContinent().equals(continent))
                .findFirst()
                .get()
                .getContinent()
        );
    }
    
    /**
     * Test of getTotalsbyContinent method, of class InternationalServiceImpl.
     */
    @Test
    public void testGetTotalsbyContinentUnhappyPath() throws Exception {
        System.out.println("getTotalsbyContinent");
        String continent = "Not continent";
        List<InternationalTotal> result = service
                .getTotalsbyContinent(continent, false, false, true, false);
        assertTrue(result.isEmpty());
    }

}
