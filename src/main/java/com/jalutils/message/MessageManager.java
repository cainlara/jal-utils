package com.jalutils.message;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class MessageManager {

  private ResourceBundle bundle;
  private String bundleName;

  private MessageManager(final String bundleName) {
    this.bundleName = bundleName;
  }

  public String getMessage(final String key) {
    return getMessage(key, new Object[0]);
  }

  public String getMessage(final String key, final Object... args) {
    MessageFormat format = new MessageFormat(getBundle().getString(key));
    return format.format(args);
  }

  private ResourceBundle getBundle() {
    if (bundle == null) {
      bundle = ResourceBundle.getBundle(bundleName);
    }

    return bundle;
  }
}
