package com.jalutils;

/**
 * Provides <code>String</code> class utilities. This class is intended to be
 * used as a Singleton Pattern implementation.
 * 
 * @author jalara
 */
public final class StringUtils {
  private static StringUtils instance;

  private StringUtils() {
    // hide constructor
  }

  /**
   * Retrives the only instance of this class.
   * 
   * @return an instance of this class.
   */
  public static StringUtils getInstance() {
    if (instance == null) {
      instance = new StringUtils();
    }

    return instance;
  }

  /**
   * Validates if a <code>string</code> instance is a blank text.
   * <P>
   * A text is considered blank as long as it fulfills at least one of the next
   * conditions:
   * <ul>
   * <li>The object is <code>null</code>.</li>
   * <li>After <i>triming</i> the lenght of the text is 0.
   * </ul>
   * 
   * @param text The <code>String</code> instance to be evaluated.
   * 
   * @return <code>true</code> if and only if the <code>text</code> parameter is
   *         valid.
   *         
   * @see String#trim()
   */
  public boolean isBlank(final String text) {
    return text == null || text.trim().isEmpty();
  }
}
