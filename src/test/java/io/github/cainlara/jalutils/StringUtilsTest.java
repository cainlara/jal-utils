package io.github.cainlara.jalutils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

  @Test
  public void testEmptyStringIsBlank() {
    assertTrue("[] is a blank string", StringUtils.getInstance().isBlank(""));
  }
  
  @Test
  public void testStringIsBlank() {
    assertFalse("[ABCD] is not a blank string", StringUtils.getInstance().isBlank("ABCD"));
  }
}
