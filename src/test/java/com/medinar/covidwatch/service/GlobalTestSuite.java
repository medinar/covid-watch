package com.medinar.covidwatch.service;

import com.medinar.covidwatch.controller.GlobalControllerIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Rommel Medina
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    GlobalControllerIT.class,
    GlobalServiceImplTest.class,
    GlobalServiceImplIT.class
})
public class GlobalTestSuite {
    // intentionally empty - Test Suite Setup (annotations) is sufficient
}
