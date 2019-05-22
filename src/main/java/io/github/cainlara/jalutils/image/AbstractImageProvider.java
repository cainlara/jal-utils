package io.github.cainlara.jalutils.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class AbstractImageProvider {

  /**
   * @see ImageManager.Builder#build()
   * 
   * @return a ImageManager instance
   */
  protected abstract ImageManager getImageManager();

  public Image getImage(final String key) {
    return getImageManager().getImage(key);
  }

  public ImageIcon getImageIcon(final String key) {
    return getImageManager().getImageIcon(key);
  }
}
