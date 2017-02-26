package com.fsbtech.interviews.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.CategoryEnum;
import com.fsbtech.interviews.utils.JSONUtils;

/**
 * Unit test on Category
 * 
 * @author Ben Chaix
 */
public class CategoryTest {

  private Category category;

  @Before
  public void setUp() {
    // initialize category
    category = new Category(CategoryEnum.TENNIS.getId(), CategoryEnum.TENNIS.getLabel());
  }

  @Test
  public void whenCheckingIfCategoryIsTennis_ThenAssertTrue() {
    Assert.assertEquals(CategoryEnum.TENNIS.getLabel(), category.getRef());
  }

  @Test
  public void whenRetrievingJSONCategory_ThenValidJSONIsReturned() {
    String jsonString = category.toJsonString();
    System.out.println(jsonString);
    Assert.assertTrue(!jsonString.equals("") && JSONUtils.isJSONValid(jsonString));
  }
}
