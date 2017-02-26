package com.fsbtech.interviews.entities;

import java.io.Serializable;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Category implements Serializable {
  private final Integer id;
  private final String ref;

  public Category(Integer id, String ref) {
    if (id == null) {
      throw new IllegalArgumentException("id can't be null");
    }
    if (ref == null) {
      throw new IllegalArgumentException("ref can't be null");
    }

    this.id = id;
    this.ref = ref;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Category)) {
      return false;
    }
    Category other = (Category) o;
    return Objects.equals(id, other.id) && Objects.equals(ref, other.ref);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ref);
  }

  public Integer getId() {
    return id;
  }

  public String getRef() {
    return ref;
  }

  public String toJsonString() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(this);
  }
}
