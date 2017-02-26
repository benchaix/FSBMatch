package com.fsbtech.interviews.entities;

import java.io.Serializable;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MarketRefType implements Serializable {
  private final Integer marketRefId;
  private final String marketRefName;

  public MarketRefType(Integer marketRefId, String marketRefName) {
    if (marketRefId == null) {
      throw new IllegalArgumentException("marketRefId can't be null");
    }
    if (marketRefName == null) {
      throw new IllegalArgumentException("marketRefName can't be null");
    }

    this.marketRefId = marketRefId;
    this.marketRefName = marketRefName;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof MarketRefType)) {
      return false;
    }
    MarketRefType other = (MarketRefType) o;
    return Objects.equals(marketRefId, other.marketRefId) && Objects.equals(marketRefName, other.marketRefName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(marketRefId, marketRefName);
  }

  public Integer getMarketRefId() {
    return marketRefId;
  }

  public String getMarketRefName() {
    return marketRefName;
  }

  public String toJsonString() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(this);
  }
}
