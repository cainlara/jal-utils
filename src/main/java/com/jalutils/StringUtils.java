package com.jalutils;

public final class StringUtils {
  private static StringUtils instance;

  private StringUtils() {
    // hide constructor
  }

  public static StringUtils getInstance() {
    if (instance == null) {
      instance = new StringUtils();
    }

    return instance;
  }

  public boolean isBlank(final String text) {
    return text == null || text.trim().isEmpty();
  }
}
