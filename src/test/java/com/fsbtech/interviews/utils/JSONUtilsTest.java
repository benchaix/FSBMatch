package com.fsbtech.interviews.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * JSON validity tests
 * 
 * @author Ben Chaix
 */
public class JSONUtilsTest {

  private String jsonStringOk = "{\"id\": 1, \"ref\": \"Football\"}";
  private String jsonStringBad = "{\"id\": 1, \"ref\": Football}}";

  @Test
  public void whenUsingJSONOk_ThenJsonIsValid() {
    boolean result = JSONUtils.isJSONValid(jsonStringOk);
    Assert.assertTrue(result);
  }

  @Test
  public void whenUsingJSONBad_ThenJsonIsInvalid() {
    boolean result = JSONUtils.isJSONValid(jsonStringBad);
    Assert.assertFalse(result);
  }
}