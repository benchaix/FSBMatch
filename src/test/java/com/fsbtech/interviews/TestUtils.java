package com.fsbtech.interviews;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.fsbtech.interviews.entities.Category;
import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;
import com.fsbtech.interviews.entities.SubCategory;

/**
 * Utils class for test
 * 
 * @author Ben Chaix
 */
public final class TestUtils {
  public enum CategoryEnum {
    FOOTBALL(1, "Football"), TENNIS(2, "Tennis"), DARTS(3, "Darts");

    final int id;
    final String label;

    CategoryEnum(int id, String label) {
      this.id = id;
      this.label = label;
    }

    public int getId() {
      return id;
    }

    public String getLabel() {
      return label;
    }
  }

  public enum SubCategoryEnum {
    FOOTBALL_PREMIER_LEAGUE(1, "Premier League", 1),
    FOOTBALL_CHAMPIONS_LEAGUE(2, "Champions League", 1),
    TENNIS_FRENCH_OPEN(3, "French Open", 2),
    TENNIS_WIMBLEDON(4, "Wimbledon", 2),
    TENNIS_AUSTRALIAN_OPEN(5, "Australian Open", 2),
    TENNIS_US_OPEN(6, "US Open", 2);

    final int id;
    final String label;
    final int catId;

    SubCategoryEnum(int id, String label, int catId) {
      this.id = id;
      this.label = label;
      this.catId = catId;
    }

    public int getId() {
      return id;
    }

    public String getLabel() {
      return label;
    }

    public int getCatId() {
      return catId;
    }
  }

  public enum MarketRefTypeEnum {
    HOME(1, "Home"), DRAW(2, "Draw"), AWAY(3, "Away"), FAKE(4, "Fake");

    final int id;
    final String label;

    MarketRefTypeEnum(int id, String label) {
      this.id = id;
      this.label = label;
    }

    public int getId() {
      return id;
    }

    public String getLabel() {
      return label;
    }
  }

  private static final SubCategoryEnum[] SUBCATS = SubCategoryEnum.values();
  private static final CategoryEnum[] CATS = CategoryEnum.values();
  private static final MarketRefTypeEnum[] MARKETS = MarketRefTypeEnum.values();
  private static final Random random = new Random();

  /**
   * get a category
   * 
   * @param id
   *          id referring to CategoryEnum
   * @return
   */
  public static Category getCategory(int id) {
    CategoryEnum cat = CATS[id - 1];
    Category category = new Category(cat.getId(), cat.getLabel());
    return category;
  }

  /**
   * get a sub category
   * 
   * @param id
   *          id referring to SubCategoryEnum
   * @return
   */
  public static SubCategory getSubCategory(int id) {
    SubCategoryEnum subCat = SUBCATS[id - 1];
    Category category = getCategory(subCat.getCatId());
    SubCategory subCategory = new SubCategory(subCat.getId(), subCat.getLabel(), category);
    return subCategory;
  }

  /**
   * get a market ref
   * 
   * @param id
   *          id referring to MarketRefTypeEnum
   * @return
   */
  public static MarketRefType getMarket(int id) {
    MarketRefTypeEnum market = MARKETS[id - 1];
    MarketRefType marketRefType = new MarketRefType(market.getId(), market.getLabel());
    return marketRefType;
  }

  /**
   * create an event with a random sub category and initialize with the Home
   * market ref
   * 
   * @param id
   *          Event id
   * @return
   */
  public static Event buildRandomTestEvent(int id) {
    // get random sub category
    SubCategoryEnum subCatEnum = SUBCATS[random.nextInt(SUBCATS.length)];
    Category category = new Category(subCatEnum.getCatId(), CATS[subCatEnum.getCatId() - 1].label);
    SubCategory subCategory = new SubCategory(subCatEnum.id, subCatEnum.label, category);

    Set<MarketRefType> marketRefTypes = new HashSet<>();
    // get Home market ref
    MarketRefType marketRefType = getMarket(MarketRefTypeEnum.HOME.getId());
    marketRefTypes.add(marketRefType);

    Event event = new Event(id, "Test Event " + id, subCategory, marketRefTypes, false);
    return event;
  }
}
