package com.fsbtech.interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;

/**
 * Concurrent test on attaching market to an event
 * 
 * @author Ben Chaix
 */
public class CustomClientConcurrentEventAttachMarketTest {

  private static CustomClient customClient = new CustomClient();

  ExecutorService executor = Executors.newCachedThreadPool();

  // add one Event
  @BeforeClass
  public static void setUp() {
    customClient.addEvent(TestUtils.buildRandomTestEvent(1));
  }

  @Test
  public void WhenAdding200Market_ThenMarketCountIs200() {

    CustomClientConcurrentTestBase callableBase = new CustomClientConcurrentTestBase();

    List<Callable<Void>> tasks = new ArrayList<>();
    for (int i = 2; i <= 200; i++) {
      MarketRefType marketRefType = new MarketRefType(i, "Test market " + i);
      tasks.add(callableBase.new AttachMarketToEvent(customClient, 1, marketRefType));
    }

    try {
      List<Future<Void>> futures = executor.invokeAll(tasks);
      for (Future<Void> future : futures) {
        future.get();
      }

    } catch (InterruptedException | ExecutionException e) {
      System.out.println(e.getMessage());
    }

    Optional<Event> optionalEvent = customClient.getEventById(1);
    if (optionalEvent.isPresent()) {
      int marketCount = optionalEvent.get().getMarketRefTypes().size();

      Assert.assertEquals(200, marketCount);

    } else {
      Assert.fail("Fail getting event1");
    }

    // debug
    System.out.println(customClient.dumpFullStructure());
  }
}
