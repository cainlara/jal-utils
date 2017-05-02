package com.jalutils.image;

import java.awt.Image;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

public class ImageManager {

  private ResourceBundle bundle;
  private String bundleName;

  private ImageManager(final String bundleName) {
    this.bundleName = bundleName;
  }

  public ImageIcon getImageIcon(final String key) {
    String path = getFilePath(key);
    URL imgURL = getClass().getResource(path);

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
}
