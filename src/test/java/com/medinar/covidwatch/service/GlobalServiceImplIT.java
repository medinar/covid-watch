package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.GlobalTotal;
import static org.assertj.core.internal.Bytes.instance;
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
public class GlobalServiceImplIT {
    
    @Autowired
    GlobalService globalService;
    
    public GlobalServiceImplIT() {
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
     * Test of getTotal method, of class GlobalServiceImpl.
     */
    @Test
    public void testGetTotal() throws Exception {
        System.out.println("getTotal");
        boolean yesterday = false;
        boolean twoDaysAgo = false;
        boolean allowNull = false;
        GlobalTotal expResult = null;
        GlobalTotal result = globalService.getTotal(yesterday, twoDaysAgo, allowNull);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
