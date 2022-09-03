package com.practice.common;

import java.util.Collection;
import java.util.List;

public final class NullOrEmpty {
  private NullOrEmpty() {
  // MAKING constructor private to make sure to disable instance creation.
  }
  public static boolean isTrue(final String s) {
    return s == null || "".equals(s.trim());
  }

  public static boolean isFalse(final String s) {
    return !isTrue(s);
  }

  public static boolean isTrue(Collection<?> cols) {
    return cols == null || cols.isEmpty();
  }
  public static boolean isFalse(Collection<?> cols) {
    return !isTrue(cols);
  }
}
