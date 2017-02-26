package com.fsbtech.interviews.entities;

import java.io.Serializable;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SubCategory implements Serializable {
  private final Integer id;
  private final String ref;
  private final Category category;

  public SubCategory(Integer id, String ref, Category category) {
    if (id == null) {
      throw new IllegalArgumentException("id can't be null");
    }
    if (ref == null) {
      throw new IllegalArgumentException("ref can't be null");
    }
    if (category == null) {
      throw new IllegalArgumentException("category can't be null");
    }

    this.id = id;
    this.ref = ref;
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof SubCategory)) {
      return false;
    }
    SubCategory other = (SubCategory) o;
    return Objects.equals(id, other.id) && Objects.equals(ref, other.ref) && Objects.equals(category, other.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ref, category);
  }

  public Integer getId() {
    return id;
  }

  public String getRef() {
    return ref;
  }

  public Category getCategory() {
    return category;
  }

  public String toJsonString() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(this);
  }
}
