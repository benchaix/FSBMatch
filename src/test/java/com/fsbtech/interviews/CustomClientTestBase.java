package com.fsbtech.interviews;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;

import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.TestUtils.SubCategoryEnum;
import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;
import com.fsbtech.interviews.entities.SubCategory;

/**
 * Base class for client test
 * 
 * @author Ben Chaix
 */
public class CustomClientTestBase {
  protected static CustomClient customClient = new CustomClient();

  protected static int EVENT1_ID = 1;
  protected static int EVENT2_ID = 2;

  protected static String EVENT1_NAME = "Andy Murray v Novak Djokovic";
  protected static String EVENT2_NAME = "Manchester United v Arsenal";

  /**
   * Initialize two events
   */
  @BeforeClass
  public static void setUp() {

    SubCategory subCategory1 = TestUtils.getSubCategory(SubCategoryEnum.TENNIS_WIMBLEDON.getId());
    SubCategory subCategory2 = TestUtils.getSubCategory(SubCategoryEnum.FOOTBALL_PREMIER_LEAGUE.getId());

    MarketRefType marketHome = TestUtils.getMarket(MarketRefTypeEnum.HOME.getId());
    MarketRefType marketAway = TestUtils.getMarket(MarketRefTypeEnum.AWAY.getId());

    Set<MarketRefType> marketRefTypes = new HashSet<>();
    marketRefTypes.add(marketHome);
    marketRefTypes.add(marketAway);

    Event event = new Event(1, EVENT1_NAME, subCategory1, marketRefTypes, false);
    customClient.addEvent(event);

    event = new Event(2, EVENT2_NAME, subCategory2, marketRefTypes, false);
    customClient.addEvent(event);
  }
}
