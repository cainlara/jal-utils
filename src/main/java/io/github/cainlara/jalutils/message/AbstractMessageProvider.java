package io.github.cainlara.jalutils.message;

public abstract class AbstractMessageProvider {

  /**
   * @see MessageManager.Builder#build()
   * 
   * @return a MessageManager instance
   */
  protected abstract MessageManager getMessageManager();

  public String getMessage(final String key) {
    return getMessageManager().getMessage(key, new Object[0]);
  }

  public String getMessage(final String key, final Object... args) {
    return getMessageManager().getMessage(key, args);
  }
}
