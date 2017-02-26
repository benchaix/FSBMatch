package com.fsbtech.interviews;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.fsbtech.interviews.entities.Event;

/**
 * Unit test on completing event
 * 
 * @author Ben Chaix
 */
public class CustomClientEventCompleteTest extends CustomClientTestBase {

  @Test
  public void whenCompletingEvent1_ThenCompleteStatusIsTrue() {
    customClient.eventCompleted(EVENT1_ID);

    Optional<Event> optionalEvent = customClient.getEventById(EVENT1_ID);
    if (optionalEvent.isPresent()) {
      Assert.assertTrue(optionalEvent.get().getCompleted());

    } else {
      Assert.fail("Fail getting event1");
    }
  }
}
