package com.fsbtech.interviews;

import java.util.Collection;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;

/**
 * Unit test on attaching market to event
 * 
 * @author Ben Chaix
 */
public class CustomClientEventAttachMarketTest extends CustomClientTestBase {

  @Test
  public void whenAttachingMarketDrawToEvent2_ThenMarketIsFound() {

    MarketRefType drawMarket = TestUtils.getMarket(MarketRefTypeEnum.DRAW.getId());
    customClient.attachMarketRefTypeToEvent(EVENT2_ID, drawMarket);

    Optional<Event> optionalEvent = customClient.getEventById(EVENT2_ID);
    if (optionalEvent.isPresent()) {
      Collection<MarketRefType> markets = optionalEvent.get().getMarketRefTypes();
      if (markets != null) {
        Assert
            .assertTrue(markets.stream().anyMatch(m -> MarketRefTypeEnum.DRAW.getLabel().equals(m.getMarketRefName())));
      } else {
        Assert.fail("Fail getting event2's market");
      }
    } else {
      Assert.fail("Fail getting event2");
    }
  }
}
