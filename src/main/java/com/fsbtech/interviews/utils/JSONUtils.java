package com.fsbtech.interviews.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * JSON Utils
 * 
 * @author Ben Chaix
 */
public final class JSONUtils {
  private static final Gson gson = new Gson();

  public static boolean isJSONValid(String jsonString) {
    try {
      gson.fromJson(jsonString, Object.class);
      return true;
    } catch (JsonSyntaxException ex) {
      return false;
    }
  }

  public static String getPrettyJSONString(String jsonString) {
    if (isJSONValid(jsonString)) {
      JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(json);
    }
    return "";
  }
}
