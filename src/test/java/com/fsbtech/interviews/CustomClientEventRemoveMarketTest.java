package com.fsbtech.interviews;

import java.util.Collection;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;

/**
 * Unit test on removing market from event
 * 
 * @author Ben Chaix
 */
public class CustomClientEventRemoveMarketTest extends CustomClientTestBase {

  @Test
  public void whenRemovingMarketAwayFromEvent2_ThenMarketIsNotFound() {

    MarketRefType awayMarket = TestUtils.getMarket(MarketRefTypeEnum.AWAY.getId());
    customClient.removeMarketRefTypeFromEvent(EVENT2_ID, awayMarket);

    Optional<Event> optionalEvent = customClient.getEventById(EVENT2_ID);
    if (optionalEvent.isPresent()) {
      Collection<MarketRefType> markets = optionalEvent.get().getMarketRefTypes();
      if (markets != null) {
        Assert.assertFalse(
            markets.stream().anyMatch(m -> MarketRefTypeEnum.AWAY.getLabel().equals(m.getMarketRefName())));
      } else {
        Assert.fail("Fail getting event2's market");
      }
    } else {
      Assert.fail("Fail getting event2");
    }
  }
}
