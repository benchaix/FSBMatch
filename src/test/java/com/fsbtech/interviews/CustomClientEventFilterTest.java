package com.fsbtech.interviews;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.CategoryEnum;
import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.TestUtils.SubCategoryEnum;

/**
 * Unit test on filtering event
 * 
 * @author Ben Chaix
 */
public class CustomClientEventFilterTest extends CustomClientTestBase {

  @Test
  public void whenFilteringEvent_ThenEvent1IsReturned() {
    List<String> filterEvent = (List<String>) customClient.futureEventNamesCollection(CategoryEnum.TENNIS.getLabel(),
        SubCategoryEnum.TENNIS_WIMBLEDON.getLabel(), MarketRefTypeEnum.AWAY.getLabel());
    Assert.assertEquals(EVENT1_NAME, filterEvent.get(0));
  }

  @Test
  public void whenFilteringAllEvent_ThenTwoEventsReturned() {
    List<String> filterEvent = (List<String>) customClient.futureEventNamesCollection(null, null, null);
    Assert.assertEquals(2, filterEvent.size());
  }

  @Test
  public void whenFilteringTennisEvent_ThenEvent1IsReturned() {
    List<String> filterEvent = (List<String>) customClient.futureEventNamesCollection(CategoryEnum.TENNIS.getLabel(),
        null, null);
    Assert.assertEquals(EVENT1_NAME, filterEvent.get(0));
  }

  @Test
  public void whenFilteringWimbledonEvent_ThenEvent1IsReturned() {
    List<String> filterEvent = (List<String>) customClient.futureEventNamesCollection(null,
        SubCategoryEnum.TENNIS_WIMBLEDON.getLabel(), null);
    Assert.assertEquals(EVENT1_NAME, filterEvent.get(0));
  }

  @Test
  public void whenFilteringMarketHomeEvent_ThenTwoEventReturned() {
    List<String> filterEvent = (List<String>) customClient.futureEventNamesCollection(null, null,
        MarketRefTypeEnum.HOME.getLabel());
    Assert.assertEquals(2, filterEvent.size());
  }

  @Test
  public void whenFilteringMarketDrawEvent_ThenNoEventReturned() {
    List<String> filterEvent = (List<String>) customClient.futureEventNamesCollection(null, null,
        MarketRefTypeEnum.DRAW.getLabel());
    Assert.assertEquals(0, filterEvent.size());
  }

  @Test
  public void whenFilteringFootballEvent_ThenEvent2IsReturned() {
    List<String> filterEvent = (List<String>) customClient.futureEventNamesCollection(CategoryEnum.FOOTBALL.getLabel(),
        null, null);
    Assert.assertEquals(EVENT2_NAME, filterEvent.get(0));
  }
}
