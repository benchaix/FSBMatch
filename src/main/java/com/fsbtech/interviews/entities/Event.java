package com.fsbtech.interviews.entities;

import java.io.Serializable;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Event implements Serializable {
  private final Integer id;
  private final String name;
  private final SubCategory subCategory;
  private final Collection<MarketRefType> marketRefTypes;
  private final Boolean completed;

  public Event(Integer id, String name, SubCategory subCategory, Collection<MarketRefType> marketRefTypes,
      Boolean completed) {
    if (id == null) {
      throw new IllegalArgumentException("id can't be null");
    }
    if (name == null) {
      throw new IllegalArgumentException("name can't be null");
    }
    if (subCategory == null) {
      throw new IllegalArgumentException("subCategory can't be null");
    }
    if (marketRefTypes == null) {
      throw new IllegalArgumentException("marketRefTypes can't be null");
    }

    this.id = id;
    this.name = name;
    this.subCategory = subCategory;
    this.marketRefTypes = marketRefTypes;
    this.completed = completed;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public SubCategory getSubCategory() {
    return subCategory;
  }

  public Collection<MarketRefType> getMarketRefTypes() {
    synchronized (marketRefTypes) {
      return marketRefTypes;
    }
  }

  public boolean hasMarketName(String marketRefName) {
    return marketRefTypes.stream().anyMatch(m -> m.getMarketRefName().equals(marketRefName));
  }

  public boolean hasMarketId(int id) {
    return marketRefTypes.stream().anyMatch(m -> m.getMarketRefId() == id);
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void attachMarketRefType(MarketRefType marketRefType) {
    synchronized (marketRefTypes) {
      this.marketRefTypes.add(marketRefType);
    }
  }

  public synchronized void removeMarketRefType(MarketRefType marketRefType) {
    synchronized (marketRefTypes) {
      this.marketRefTypes.remove(marketRefType);
    }
  }

  public String toJsonString() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(this);
  }
}
