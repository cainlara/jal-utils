package io.github.cainlara.jalutils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileUtilsTest {

  @Test
  public void addExtension() {
    String fileName = "GenericName";
    String extension = "docx";
    
    assertEquals("Generated file name must be equals", fileName + '.' + extension, FileUtils.getInstance().addExtension(fileName, extension));
  }

}
