package com.fsbtech.interviews.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.utils.JSONUtils;

/**
 * Unit test on MarketRefType
 * 
 * @author Ben Chaix
 */
public class MarketRefTypeTest {

  private MarketRefType marketRefType;

  @Before
  public void setUp() {
    // initialize market ref type
    marketRefType = new MarketRefType(MarketRefTypeEnum.HOME.getId(), MarketRefTypeEnum.HOME.getLabel());
  }

  @Test
  public void whenCheckingMarketHome_ThenAssertTrue() {
    Assert.assertEquals(MarketRefTypeEnum.HOME.getLabel(), marketRefType.getMarketRefName());
  }

  @Test
  public void whenRetrievingJSONMarket_ThenValidJSONIsReturned() {
    String jsonString = marketRefType.toJsonString();
    System.out.println(jsonString);
    Assert.assertTrue(!jsonString.equals("") && JSONUtils.isJSONValid(jsonString));
  }
}
