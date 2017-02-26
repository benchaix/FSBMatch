package com.fsbtech.interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

/**
 * Concurrent test on adding event
 * 
 * @author Ben Chaix
 */
public class CustomClientConcurrentEventAddTest {

  private static CustomClient customClient = new CustomClient();

  ExecutorService executor = Executors.newCachedThreadPool();

  @Test
  public void WhenAdding500000Events_ThenEventCountIs500000() {

    CustomClientConcurrentTestBase callableBase = new CustomClientConcurrentTestBase();

    List<Callable<Void>> tasks = new ArrayList<>();
    for (int i = 1; i <= 500000; i++) {
      tasks.add(callableBase.new AddEvent(customClient, i));
    }

    try {
      List<Future<Void>> futures = executor.invokeAll(tasks);
      for (Future<Void> future : futures) {
        future.get();
      }

    } catch (InterruptedException | ExecutionException e) {
      System.out.println(e.getMessage());
    }

    int eventCount = customClient.futureEventNamesCollection(null, null, null).size();

    Assert.assertEquals(500000, eventCount);
  }
}
