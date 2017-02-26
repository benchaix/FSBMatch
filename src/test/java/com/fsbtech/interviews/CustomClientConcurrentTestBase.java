package com.fsbtech.interviews;

import java.util.concurrent.Callable;

import com.fsbtech.interviews.entities.MarketRefType;

/**
 * Base class for concurrent client test
 * 
 * @author Ben Chaix
 */
public class CustomClientConcurrentTestBase {

  public class AddEvent implements Callable<Void> {
    final Client customClient;
    final Integer id;

    AddEvent(Client customClient, int id) {
      this.customClient = customClient;
      this.id = id;
    }

    @Override
    public Void call() throws Exception {
      customClient.addEvent(TestUtils.buildRandomTestEvent(id));
      return null;
    }
  }

  public class CompleteEvent implements Callable<Void> {
    final Client customClient;
    final Integer id;

    CompleteEvent(Client customClient, int id) {
      this.customClient = customClient;
      this.id = id;
    }

    @Override
    public Void call() throws Exception {
      customClient.eventCompleted(id);
      return null;
    }
  }

  public class AttachMarketToEvent implements Callable<Void> {
    final Client customClient;
    final Integer id;
    final MarketRefType marketRefType;

    AttachMarketToEvent(Client customClient, int id, MarketRefType marketRefType) {
      this.customClient = customClient;
      this.id = id;
      this.marketRefType = marketRefType;
    }

    @Override
    public Void call() throws Exception {
      customClient.attachMarketRefTypeToEvent(id, marketRefType);
      return null;
    }
  }

  public class RemoveMarketFromEvent implements Callable<Void> {
    final Client customClient;
    final Integer id;
    final MarketRefType marketRefType;

    RemoveMarketFromEvent(Client customClient, int id, MarketRefType marketRefType) {
      this.customClient = customClient;
      this.id = id;
      this.marketRefType = marketRefType;
    }

    @Override
    public Void call() throws Exception {
      customClient.removeMarketRefTypeFromEvent(id, marketRefType);
      return null;
    }
  }

  public class FilterEvent implements Callable<Void> {
    final Client customClient;
    final String cat;
    final String subCat;
    final String marketRefName;

    FilterEvent(Client customClient, String cat, String subCat, String marketRefName) {
      this.customClient = customClient;
      this.cat = cat;
      this.subCat = subCat;
      this.marketRefName = marketRefName;
    }

    @Override
    public Void call() throws Exception {
      customClient.futureEventNamesCollection(cat, subCat, marketRefName);
      return null;
    }
  }

  public class DumpFullStructure implements Callable<Void> {
    final Client customClient;

    DumpFullStructure(Client customClient) {
      this.customClient = customClient;
    }

    @Override
    public Void call() throws Exception {
      customClient.dumpFullStructure();
      return null;
    }
  }
}
