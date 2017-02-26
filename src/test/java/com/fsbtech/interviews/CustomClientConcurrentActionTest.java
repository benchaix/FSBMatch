package com.fsbtech.interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.CategoryEnum;
import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.entities.MarketRefType;

/**
 * Concurrent test with mixed actions
 * 
 * @author Ben Chaix
 */
public class CustomClientConcurrentActionTest {

  private static CustomClient customClient = new CustomClient();

  ExecutorService executor = Executors.newCachedThreadPool();

  // add 1000 Events
  @BeforeClass
  public static void setUp() {
    for (int i = 1; i <= 1000; i++) {
      customClient.addEvent(TestUtils.buildRandomTestEvent(i));
    }
  }

  @Test
  public void WhenCallingConcurrentOperation_ShouldSucceed() {

    CustomClientConcurrentTestBase callableBase = new CustomClientConcurrentTestBase();
    List<Callable<Void>> tasks = new ArrayList<>();

    // dump full structure
    for (int i = 0; i < 400; i++) {
      tasks.add(callableBase.new DumpFullStructure(customClient));
    }

    // attach Draw market to event
    for (int i = 250; i <= 1000; i++) {
      MarketRefType marketRefType = TestUtils.getMarket(MarketRefTypeEnum.DRAW.getId());
      tasks.add(callableBase.new AttachMarketToEvent(customClient, i, marketRefType));
    }

    // complete 500 first events
    for (int i = 1; i <= 500; i++) {
      tasks.add(callableBase.new CompleteEvent(customClient, i));
    }

    // remove Home market from event
    for (int i = 250; i <= 750; i++) {
      MarketRefType marketRefType = TestUtils.getMarket(MarketRefTypeEnum.HOME.getId());
      tasks.add(callableBase.new RemoveMarketFromEvent(customClient, i, marketRefType));
    }

    // filter event
    for (int i = 0; i < 1000; i++) {
      tasks.add(callableBase.new FilterEvent(customClient, CategoryEnum.FOOTBALL.getLabel(), null, null));
    }

    try {
      List<Future<Void>> futures = executor.invokeAll(tasks);
      for (Future<Void> future : futures) {
        future.get();
      }

    } catch (InterruptedException | ExecutionException e) {
      System.out.println(e.getMessage());
    }

    // 500 events not completed
    int eventCount = customClient.futureEventNamesCollection(null, null, null).size();
    Assert.assertEquals(500, eventCount);

    // 500 events not completed with Draw market
    int eventWithDrawMarketCount = customClient
        .futureEventNamesCollection(null, null, MarketRefTypeEnum.DRAW.getLabel())
        .size();
    Assert.assertEquals(500, eventWithDrawMarketCount);

    // 250 events not completed with Home market
    int eventWithHomeMarketCount = customClient
        .futureEventNamesCollection(null, null, MarketRefTypeEnum.HOME.getLabel())
        .size();
    Assert.assertEquals(250, eventWithHomeMarketCount);
  }
}
