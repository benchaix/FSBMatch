package com.fsbtech.interviews.entities;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fsbtech.interviews.TestUtils.CategoryEnum;
import com.fsbtech.interviews.TestUtils.MarketRefTypeEnum;
import com.fsbtech.interviews.TestUtils.SubCategoryEnum;
import com.fsbtech.interviews.utils.JSONUtils;

/**
 * Unit test on Event
 * 
 * @author Ben Chaix
 */
public class EventTest {

  private Event event;

  @Before
  public void setUp() {
    // initialize category and sub category
    Category category = new Category(CategoryEnum.TENNIS.getId(), CategoryEnum.TENNIS.getLabel());
    SubCategory subCategory = new SubCategory(SubCategoryEnum.TENNIS_FRENCH_OPEN.getId(),
        SubCategoryEnum.TENNIS_FRENCH_OPEN.getLabel(), category);

    // initialize market ref type
    Set<MarketRefType> marketRefTypes = new HashSet<>();
    marketRefTypes.add(new MarketRefType(MarketRefTypeEnum.HOME.getId(), MarketRefTypeEnum.HOME.getLabel()));
    marketRefTypes.add(new MarketRefType(MarketRefTypeEnum.FAKE.getId(), MarketRefTypeEnum.FAKE.getLabel()));

    event = new Event(1, "Andy Murray v Novak Djokovic", subCategory, marketRefTypes, false);
  }

  @Test
  public void whenCheckingIfCategoryIsTennis_ThenAssertTrue() {
    Assert.assertEquals(CategoryEnum.TENNIS.getLabel(), event.getSubCategory().getCategory().getRef());
  }

  @Test
  public void whenCheckingIfSubCategoryIsFrenchOpen_ThenAssertTrue() {
    Assert.assertEquals(SubCategoryEnum.TENNIS_FRENCH_OPEN.getLabel(), event.getSubCategory().getRef());
  }

  @Test
  public void whenCheckingMarketHome_ThenAssertTrue() {
    Assert.assertEquals(true, event.hasMarketName(MarketRefTypeEnum.HOME.getLabel()));
  }

  @Test
  public void whenCheckingMarketDraw_ThenAssertFalse() {
    Assert.assertEquals(false, event.hasMarketName(MarketRefTypeEnum.DRAW.getLabel()));
  }

  @Test
  public void whenCheckingCompletedStatus_ThenAssertFalse() {
    Assert.assertEquals(false, event.getCompleted());
  }

  @Test
  public void whenAttachAwayMarket_ThenMarketSizeShouldBeOneMore() {
    int initialSize = event.getMarketRefTypes().size();

    MarketRefType awayMarket = new MarketRefType(MarketRefTypeEnum.AWAY.getId(), MarketRefTypeEnum.AWAY.getLabel());
    event.attachMarketRefType(awayMarket);

    Assert.assertEquals(initialSize + 1, event.getMarketRefTypes().size());
  }

  @Test
  public void whenRemoveFakeMarket_ThenMarketSizeShouldBeOneLess() {
    int initialSize = event.getMarketRefTypes().size();

    MarketRefType awayMarket = new MarketRefType(MarketRefTypeEnum.FAKE.getId(), MarketRefTypeEnum.FAKE.getLabel());
    event.removeMarketRefType(awayMarket);

    Assert.assertEquals(initialSize - 1, event.getMarketRefTypes().size());
  }

  @Test
  public void whenRetrievingJSONEvent_ThenValidJSONIsReturned() {
    String jsonString = event.toJsonString();
    System.out.println(jsonString);
    Assert.assertTrue(!jsonString.equals("") && JSONUtils.isJSONValid(jsonString));
  }
}
