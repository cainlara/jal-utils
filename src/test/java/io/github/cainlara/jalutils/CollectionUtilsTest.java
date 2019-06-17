package io.github.cainlara.jalutils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {
  
  @Test
  public void nullListsAreEqualsTest() {
    List<String> l1 = null;
    List<String> l2 = null;

    assertTrue("Null lists must be equal", CollectionUtils.getInstance().sortedListsAreEqual(l1, l2));
  }
  
  @Test
  public void emptyListsAreEqualsTest() {
    List<String> l1 = new ArrayList<>();
    List<String> l2 = new ArrayList<>();

    assertTrue("Empty lists must be equal", CollectionUtils.getInstance().sortedListsAreEqual(l1, l2));
  }

  @Test
  public void notNullNotEmptySortedListsAreEqualsTest() {
    List<String> l1 = Arrays.asList("Larry", "Curly", "Moe");
    List<String> l2 = new ArrayList<>(l1);

    assertTrue("Not null, not empty lists must be equal", CollectionUtils.getInstance().sortedListsAreEqual(l1, l2));
  }
  
  @Test
  public void notNullNotEmptySortedListsAreNotEqualsTest() {
    List<String> l1 = Arrays.asList("Larry", "Curly", "Moe");
    List<String> l2 = Arrays.asList("Car", "Boat", "Plane");

    assertFalse("Not null, not empty list must not be equal", CollectionUtils.getInstance().sortedListsAreEqual(l1, l2));
  }
  
  @Test
  public void notNullNotEmptyListsAreEqualsTest() {
    List<String> l1 = Arrays.asList("Larry", "Curly", "Moe");
    List<String> l2 = Arrays.asList("Moe", "Larry", "Curly");

    assertTrue("Not null, not empty lists must be equal", CollectionUtils.getInstance().listsAreEqual(l1, l2));
  }
  
  @Test
  public void notNullNotEmptyListsAreNotEqualsTest() {
    List<String> l1 = Arrays.asList("Larry", "Curly", "Moe");
    List<String> l2 = Arrays.asList("Moe", "Larry", "Curly", "Larry");

    assertFalse("Not null, not empty lists must be equal", CollectionUtils.getInstance().listsAreEqual(l1, l2));
  }

}
