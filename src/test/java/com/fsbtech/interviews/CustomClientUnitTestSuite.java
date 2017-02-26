package com.fsbtech.interviews;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit test suite for basic unit test
 * 
 * @author Ben Chaix
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ CustomClientEventAddedTest.class, CustomClientEventFilterTest.class,
    CustomClientEventAttachMarketTest.class, CustomClientEventRemoveMarketTest.class,
    CustomClientEventCompleteTest.class, CustomClientEventDumpTest.class })

public class CustomClientUnitTestSuite {

}
