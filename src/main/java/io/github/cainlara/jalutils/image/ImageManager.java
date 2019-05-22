package io.github.cainlara.jalutils.image;

import java.awt.Image;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

public final class ImageManager {
  private ResourceBundle bundle;
  private String bundleName;
  private Class<? extends AbstractImageProvider> clazz;

  private ImageManager(final Builder builder) {
    this.bundleName = builder.bundleName;
    this.clazz = builder.clazz;
  }

  public ImageIcon getImageIcon(final String key) {
    String path = getFilePath(key);
    URL imgURL = getClazz().getResource(path);

    if (imgURL != null) {
      return new ImageIcon(imgURL);
    }

    return null;
  }

  public Image getImage(final String key) {
    ImageIcon imgIcon = getImageIcon(key);

    return imgIcon == null ? null : imgIcon.getImage();
  }

  private String getFilePath(final String key) {
    return getBundle().getString(key);
  }

  private ResourceBundle getBundle() {
    if (bundle == null) {
      bundle = ResourceBundle.getBundle(bundleName);
    }

    return bundle;
  }
  
  private Class<? extends AbstractImageProvider> getClazz() {
    return clazz;
  }

  public static class Builder {
    private String bundleName;
    private Class<? extends AbstractImageProvider> clazz;

    public Builder(final String bundleName, final Class<? extends AbstractImageProvider> clazz) {
      this.bundleName = bundleName;
      this.clazz = clazz;
    }

    public ImageManager build() {
      return new ImageManager(this);
    }
  }
}
