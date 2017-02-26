package com.fsbtech.interviews;

import org.junit.Assert;
import org.junit.Test;

import com.fsbtech.interviews.utils.JSONUtils;

/**
 * Unit test on dumping structure
 * 
 * @author Ben Chaix
 */
public class CustomClientEventDumpTest extends CustomClientTestBase {

  @Test
  public void whenDumpingFullStructure_ThenValidJSONIsReturned() {
    String jsonString = customClient.dumpFullStructure();
    System.out.println(jsonString);
    Assert.assertTrue(!jsonString.equals("") && JSONUtils.isJSONValid(jsonString));
  }
}
