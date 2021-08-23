package com.medinar.covidwatch.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Rommel Medina
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.medinar.covidwatch.controller.InternationalControllerIT.class,
    com.medinar.covidwatch.service.InternationalServiceImplTest.class, 
    com.medinar.covidwatch.service.InternationalServiceImplIT.class
})
public class InternationalTestSuite {
    // intentionally empty - Test Suite Setup (annotations) is sufficient
}
