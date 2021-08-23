package com.medinar.covidwatch.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Rommel Medina
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
//    InternationalServiceImplTest.class, 
    ContinentalServiceImplIT.class, 
    InternationalServiceImplIT.class, 
    GlobalServiceImplIT.class
})
public class CovidWatchServiceTestSuite {
    // intentionally empty - Test Suite Setup (annotations) is sufficient
}
