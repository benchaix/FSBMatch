package com.fsbtech.interviews;

import java.util.Collection;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.TestUtils.SubCategoryEnum;
import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;
import com.fsbtech.interviews.entities.SubCategory;

/**
 * Unit test on added event
 * 
 * @author Ben Chaix
 */
public class CustomClientEventAddedTest extends CustomClientTestBase {

  @Test
  public void whenGettingEvent1_ThenNotNullEvent() {
    Optional<Event> optionalEvent = customClient.getEventById(EVENT1_ID);
    Assert.assertNotNull(optionalEvent.get());
  }

  @Test
  public void whenGettingUnknownEvent_ThenNullEvent() {
    Optional<Event> optionalEvent = customClient.getEventById(3250);
    Assert.assertFalse(optionalEvent.isPresent());
  }

  @Test
  public void whenGettingEvent1SubCategory_ThenEvent1SubCategoryIsTennisWimbledon() {
    Optional<Event> optionalEvent = customClient.getEventById(EVENT1_ID);
    if (optionalEvent.isPresent()) {
      SubCategory subCategory = optionalEvent.get().getSubCategory();
      if (subCategory != null) {
        Assert.assertEquals(SubCategoryEnum.TENNIS_WIMBLEDON.getLabel(), subCategory.getRef());
      } else {
        Assert.fail("Fail getting event1's sub category");
      }
    } else {
      Assert.fail("Fail getting event1");
    }
  }

  @Test
  public void whenGettingEvent1Markets_ThenEvent1MarketAreHomeAndAway() {
    Optional<Event> optionalEvent = customClient.getEventById(EVENT1_ID);
    if (optionalEvent.isPresent()) {
      Collection<MarketRefType> markets = optionalEvent.get().getMarketRefTypes();
      if (markets != null) {
        Assert.assertEquals(2, markets.size());
        Assert
            .assertTrue(markets.stream().anyMatch(m -> MarketRefTypeEnum.HOME.getLabel().equals(m.getMarketRefName())));
        Assert
            .assertTrue(markets.stream().anyMatch(m -> MarketRefTypeEnum.AWAY.getLabel().equals(m.getMarketRefName())));
      } else {
        Assert.fail("Fail getting event1's market");
      }
    } else {
      Assert.fail("Fail getting event1");
    }
  }

  @Test
  public void whenGettingEvent1CompleteStatus_ThenCompleteStatusIsFalse() {
    Optional<Event> optionalEvent = customClient.getEventById(EVENT1_ID);
    if (optionalEvent.isPresent()) {
      Assert.assertFalse(optionalEvent.get().getCompleted());
    } else {
      Assert.fail("Fail getting event1");
    }
  }
}
