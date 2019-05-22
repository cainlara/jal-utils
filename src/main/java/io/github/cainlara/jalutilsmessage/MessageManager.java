package io.github.cainlara.jalutilsmessage;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class MessageManager {
  private ResourceBundle bundle;
  private String bundleName;

  private MessageManager(final Builder builder) {
    this.bundleName = builder.bundleName;
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

  public static class Builder {
    private String bundleName;

    public Builder(final String bundleName) {
      this.bundleName = bundleName;
    }

    public MessageManager build() {
      return new MessageManager(this);
    }
  }
}
