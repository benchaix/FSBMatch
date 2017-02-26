package com.fsbtech.interviews.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.CategoryEnum;
import com.fsbtech.interviews.TestUtils.SubCategoryEnum;
import com.fsbtech.interviews.utils.JSONUtils;

/**
 * Unit test on SubCategory
 * 
 * @author Ben Chaix
 */
public class SubCategoryTest {

  private SubCategory subCategory;

  @Before
  public void setUp() {
    // initialize sub category
    Category category = new Category(CategoryEnum.TENNIS.getId(), CategoryEnum.TENNIS.getLabel());
    subCategory = new SubCategory(SubCategoryEnum.TENNIS_FRENCH_OPEN.getId(),
        SubCategoryEnum.TENNIS_FRENCH_OPEN.getLabel(), category);
  }

  @Test
  public void whenCheckingIfCategoryIsTennis_ThenAssertTrue() {
    Assert.assertEquals(CategoryEnum.TENNIS.getLabel(), subCategory.getCategory().getRef());
  }

  @Test
  public void whenCheckingIfSubCategoryIsFrenchOpen_ThenAssertTrue() {
    Assert.assertEquals(SubCategoryEnum.TENNIS_FRENCH_OPEN.getLabel(), subCategory.getRef());
  }

  @Test
  public void whenRetrievingJSONSubCategory_ThenValidJSONIsReturned() {
    String jsonString = subCategory.toJsonString();
    System.out.println(jsonString);
    Assert.assertTrue(!jsonString.equals("") && JSONUtils.isJSONValid(jsonString));
  }
}
