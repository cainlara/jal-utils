package io.github.cainlara.jalutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CollectionUtils {
  private static CollectionUtils instance;

  private CollectionUtils() {
    // hide constructor
  }

  /**
   * Retrives the only instance of this class.
   * 
   * @return an instance of this class.
   */
  public static CollectionUtils getInstance() {
    if (instance == null) {
      instance = new CollectionUtils();
    }

    return instance;
  }

  /**
   * Splits a <code>String</code> into an <code>ArrayList</code>.
   * 
   * @param str The <code>String</code> to split.
   * 
   * @return
   *         <ul>
   *         <li><code>null</code> if the input is <code>null</code>.</li>
   *         <li>An empty <code>ArrayList</code> if lenght of input is 0.</li>
   *         <li>An <code>ArrayList</code> of <code>String</code> containing every
   *         single characther from the original input, including blank
   *         spaces.</li>
   *         </ul>
   */
  public List<String> toList(final String str) {
    return toList(str, true);
  }

  /**
   * Splits a <code>String</code> into an <code>ArrayList</code>.
   * 
   * @param str           The <code>String</code> to split.
   * @param includeBlanks whether or not to include blank spaces from original
   *                      input.
   * 
   * @return
   *         <ul>
   *         <li><code>null</code> if the input is <code>null</code>.</li>
   *         <li>An empty <code>ArrayList</code> if lenght of input is 0.</li>
   *         <li>An <code>ArrayList</code> of <code>String</code> containing every
   *         single characther from the original input, including blank
   *         spaces.</li>
   *         </ul>
   */
  public List<String> toList(final String str, boolean includeBlanks) {
    List<String> list = null;

    if (str != null) {
      list = new ArrayList<>();

      for (char c : str.toCharArray()) {
        if (!String.valueOf(c).equals(" ") || (String.valueOf(c).equals(" ") && includeBlanks)) {
          list.add(String.valueOf(c));
        }
      }
    }

    return list;
  }

  /**
   * Evaluates if the contents of two <code>ArrayList</code> are equal.
   * <p>
   * This method does not compare references. It only compares the equality of the
   * content of two <code>ArrayList</code> instances. The contents of two
   * <code>ArrayList</code> instances is evaluated to equal when one of the next
   * three cases occurs:
   * 
   * <ul>
   * <li>If one of the list is <code>null</code> both lists should be
   * <code>null</code>.</li>
   * <li>If one of the list is empty (<code>isEmpty() == true</code>) both lists
   * should be empty.</li>
   * <li>The sizes of both list must be the same <b>and</b> each element in
   * <i>n</i> position from one list must be equal to the object in the same
   * position from the other list.</li>
   * </ul>
   * 
   * @param l1 List 1.
   * @param l2 List 2.
   * 
   * @return <code>true</code> if and only if the content of boths list is
   *         evaluated to equal. Otherwise returns <code>false</code>.
   */
  public boolean sortedListsAreEqual(final List<?> l1, final List<?> l2) {
    if (l1 == null || l2 == null) {
      return l1 == null && l2 == null;
    }

    if (l1.isEmpty() || l2.isEmpty()) {
      return l1.isEmpty() && l2.isEmpty();
    }

    if (l1.size() != l2.size()) {
      return false;
    }

    for (int index = 0; index < l1.size(); index++) {
      Object o1 = l1.get(index);
      Object o2 = l2.get(index);

      if (!o1.equals(o2)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Evaluates if the contents of two <code>ArrayList</code> are equal.
   * <p>
   * This method does not compare references. It only compares the equality of the
   * content of two <code>ArrayList</code> instances. The contents of two
   * <code>ArrayList</code> instances is evaluated to equal when one of the next
   * three cases occurs:
   * 
   * <ul>
   * <li>If one of the list is <code>null</code> both lists should be
   * <code>null</code>.</li>
   * <li>If one of the list is empty (<code>isEmpty() == true</code>) both lists
   * should be empty.</li>
   * <li>The sizes of both list must be the same <b>and</b> each element in one
   * list must have one and only one reflected element in the other list,
   * regardless of their positions in their respective lists.</li>
   * </ul>
   * 
   * @param l1 List 1.
   * @param l2 List 2.
   * 
   * @return <code>true</code> if and only if the content of boths list is
   *         evaluated to equal. Otherwise returns <code>false</code>.
   */
  public boolean listsAreEqual(final List<?> l1, final List<?> l2) {
    if (l1 == null || l2 == null) {
      return l1 == null && l2 == null;
    }

    if (l1.isEmpty() || l2.isEmpty()) {
      return l1.isEmpty() && l2.isEmpty();
    }

    if (l1.size() != l2.size()) {
      return false;
    }

    Map<String, Integer> l1Hashed = getHashedValues(l1);
    Map<String, Integer> l2Hashed = getHashedValues(l2);

    for (Object o1 : l1) {
      String o1Str = o1.toString();
      int o1Hash = l1Hashed.get(o1Str);

      if (l2Hashed.containsKey(o1Str)) {
        int o2Hash = l2Hashed.get(o1Str);

        if (o1Hash != o2Hash) {
          return false;
        }
      } else {
        return false;
      }
    }

    return true;
  }

  private Map<String, Integer> getHashedValues(final List<?> list) {
    Map<String, Integer> hashedValues = null;

    if (list != null) {
      hashedValues = new HashMap<>();

      if (!list.isEmpty()) {
        for (Object o : list) {
          hashedValues.put(o.toString(), o.hashCode());
        }
      }
    }

    return hashedValues;
  }

}
