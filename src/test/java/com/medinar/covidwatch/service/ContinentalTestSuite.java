package com.medinar.covidwatch.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Rommel Medina
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.medinar.covidwatch.controller.ContinentalControllerIT.class,
    com.medinar.covidwatch.service.ContinentalServiceImplTest.class, 
    com.medinar.covidwatch.service.ContinentalServiceImplIT.class
})
public class ContinentalTestSuite {
    // intentionally empty - Test Suite Setup (annotations) is sufficient
}
