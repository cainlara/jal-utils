package io.github.cainlara.jalutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Handles file system operations. This class is intended to be used as a
 * Singleton Pattern implementation.
 * 
 * @author jalara
 */
public final class FileUtils {
  private static final String FILE_CANT_BE_NULL_MESSAGE = "File can not be null";
  private static final String DOT_CHARACTER = ".";

  private static FileUtils instance;

  private FileUtils() {
    // hide constructor
  }

  /**
   * Retrives the only instance of this class.
   * 
   * @return an instance of this class.
   */
  public static FileUtils getInstance() {
    if (instance == null) {
      instance = new FileUtils();
    }

    return instance;
  }

  /**
   * Appends the specified extension to the file name.
   * <p>
   * If the file's name does not end with the the extension specified by param
   * <code>extension</code>, then the extension is appended. <b>NOTE: </b>
   * invoking this method does not modify the file's name in the local file
   * system, nor the JVM reference to the file.
   * 
   * @param fileName  the current file name.
   * @param extension the extension to append.
   * 
   * @return the fixed file's name ending with the extension specification.
   */
  public String addExtension(final String fileName, final String extension) {
    if (StringUtils.getInstance().isBlank(fileName)) {
      throw new IllegalArgumentException("FileName can not be null nor empty.");
    }

    String fixedFileName = fileName;
    String fixedExtension = null;
    
    if (extension.startsWith(DOT_CHARACTER)) {
      fixedExtension = extension;
    } else {
      fixedExtension = String.join("", DOT_CHARACTER, extension);
    }

    if (!fixedFileName.endsWith(fixedExtension)) {
      fixedFileName += fixedExtension;
    }

    return fixedFileName;
  }

  /**
   * Copies the file in the file system represented by param
   * <code>sourceFile</code> into the destination specified by param
   * <code>targetFile</code>.
   * 
   * @param sourceFile The file to be copied.
   * @param targetFile Copy destination.
   * 
   * @throws IOException if the file specified by <code>sourceFile</code> is
   *                     <code>null</code> or does not exist in the file system,
   *                     or if the file specified by <code>targetFile</code> is
   *                     <code>null</code>.
   */
  public void copyFile(final File sourceFile, final File targetFile) throws IOException {
    if (sourceFile == null) {
      throw new IOException("Source file can not be null.");
    }

    if (targetFile == null) {
      throw new IOException("Target file can not be null.");
    }

    if (!sourceFile.exists()) {
      throw new IOException("Source file (" + sourceFile.getAbsolutePath() + ") does not exist.");
    }

    Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
  }

  /**
   * Deletes a file from the filesystem.
   * 
   * @param file2delete a <code>File</code> object that represents the file to
   *                    delete.
   * @return <code>true</code> if and only if the file is successfully deleted;
   *         <code>false</code> otherwise
   * @throws IOException if the file does not exist in the file system or if the
   *                     param <code>file2delete</code> represents a directory.
   * 
   * @see File
   */
  public boolean deleteFile(final File file2delete) throws IOException {
    if (file2delete == null) {
      throw new IOException("File to delete can not be null.");
    }

    if (!file2delete.exists()) {
      throw new IOException("File to delete does not exist.");
    }

    if (file2delete.isDirectory()) {
      throw new IOException(file2delete.getAbsolutePath() + " is a Folder.");
    }

    return Files.deleteIfExists(file2delete.toPath());
  }

  /**
   * Deletes a file from the filesystem.
   * 
   * @param fileAbsolutePath the absolute path to the directory to delete.
   * @return <code>true</code> if and only if the file is successfully deleted;
   *         <code>false</code> otherwise
   * @throws IOException if the file does not exist in the filesystem or if the
   *                     param <code>fileAbsolutePath</code> represents a
   *                     directory.
   */
  public boolean deleteFile(final String fileAbsolutePath) throws IOException {
    if (StringUtils.getInstance().isBlank(fileAbsolutePath)) {
      throw new IllegalArgumentException("Invalid file path");
    }

    return deleteFile(new File(fileAbsolutePath));
  }

  /**
   * Deletes a directory and its content from the filesystem.
   * 
   * @param folder2Delete a <code>File</code> object that represents the directory
   *                      to delete.
   * @return <code>true</code> if and only if the directory is successfully
   *         deleted; <code>false</code> otherwise
   * @throws IOException if the directory does not exist in the file system, if
   *                     the param <code>folderAbsolutePath</code> represents a
   *                     file, or if any element from the directory's content can
   *                     not be deleted.
   * 
   * @see File
   */
  public boolean deleteFolder(final File folder2Delete) throws IOException {
    if (folder2Delete == null) {
      throw new IllegalArgumentException("Folder to delete can not be null.");
    }

    if (!folder2Delete.exists()) {
      throw new IOException("Folder to delete does not exist.");
    }

    if (folder2Delete.isFile()) {
      throw new IOException(folder2Delete.getAbsolutePath() + " is a File.");
    }

    File[] folderContent = folder2Delete.listFiles();

    if (folderContent == null || folderContent.length == 0) {
      return Files.deleteIfExists(folder2Delete.toPath());
    }

    for (File file : folderContent) {
      if (file.isDirectory()) {
        if (!deleteFolder(file)) {
          throw new IOException("Impossible to delete folder " + file.getAbsolutePath());
        }
      } else if (file.isFile() && !deleteFile(file)) {
        throw new IOException("Impossible to delete file " + file.getAbsolutePath());
      }
    }

    return Files.deleteIfExists(folder2Delete.toPath());
  }

  /**
   * Deletes a directory and its content from the filesystem.
   * 
   * @param folderAbsolutePath the absolute path to the directory to delete.
   * @return <code>true</code> if and only if the directory is successfully
   *         deleted; <code>false</code> otherwise
   * @throws IOException if the directory does not exist in the file system or if
   *                     the param <code>folderAbsolutePath</code> represents a
   *                     file.
   */
  public boolean deleteFolder(final String folderAbsolutePath) throws IOException {
    if (StringUtils.getInstance().isBlank(folderAbsolutePath)) {
      throw new IllegalArgumentException("Invalid directory path");
    }

    return deleteFolder(new File(folderAbsolutePath));
  }

  /**
   * Retrieves the extension of the file represented by param <code>file</code>.
   * <p>
   * The file name must follow the pattern <i>file_name.file_extension</i>,
   * allowing this method to retrieve the substring starting at the last index of
   * the dot character.
   * 
   * @param file a <code>java.io.File</code> instance to retrieve the extension
   *             from.
   * 
   * @return The file extension.
   */
  public String getFileExtension(final File file) {
    if (file == null) {
      throw new IllegalArgumentException(FILE_CANT_BE_NULL_MESSAGE);
    }

    return getFileExtension(file.getAbsolutePath());
  }

  /**
   * Retrieves the extension of a file located at <code>filePath</code>.
   * <p>
   * The file name must follow the pattern <i>file_name.file_extension</i>,
   * allowing this method to retrieve the substring starting at the last index of
   * the dot character.
   * 
   * @param filePath a valid <code>String</code> path to the file to retrieve the
   *                 extension from.
   * 
   * @return The file extension.
   */
  public String getFileExtension(final String filePath) {
    String extension = null;

    if (StringUtils.getInstance().isBlank(filePath)) {
      throw new IllegalArgumentException("File path is invalid");
    }

    if (filePath.indexOf('.') < 0) {
      throw new IllegalArgumentException(
          "File exstension from " + filePath + " can not be found because path does not contain dot character");
    }

    extension = filePath.substring(filePath.lastIndexOf('.') + 1);

    return extension;
  }

  /**
   * Retrieves the container folder of a file.
   * 
   * @param file the <code>java.io.File</code>instance to retrieve the container
   *             folder from.
   * 
   * @return a <code>java.io.File</code> instance representing a
   *         <code>directory</code> which contains the input file.
   */
  public File getParentFolder(final File file) {
    if (file == null) {
      throw new IllegalArgumentException(FILE_CANT_BE_NULL_MESSAGE);
    }

    if (!file.exists()) {
      throw new IllegalArgumentException("File " + file.getAbsolutePath() + " does not exist.");
    }

    return file.getParentFile();
  }

  /**
   * Retrieves the container folder of a file.
   * 
   * @param filePath a valid <code>String</code> path to the file to retrieve the
   *                 container folder from.
   * 
   * @return a <code>java.io.File</code> instance representing a
   *         <code>directory</code> which contains the input file.
   */
  public File getParentFolder(final String filePath) {
    if (StringUtils.getInstance().isBlank(filePath)) {
      throw new IllegalArgumentException("Invalid file path.");
    }

    return getParentFolder(new File(filePath));
  }

  /**
   * Checks if a <code>java.io.File</code> instance exists in the local file
   * system.
   * 
   * @param file the <code>java.io.File</code>instance to validate.
   * 
   * @return <code>true</code> if and only if the <code>java.io.File</code>
   *         instance parameter represents an existent file.
   */
  public boolean exists(final File file) {
    if (file == null) {
      throw new IllegalArgumentException(FILE_CANT_BE_NULL_MESSAGE);
    }

    return file.exists();
  }

  /**
   * Checks if a <code>java.io.File</code> instance exists in the local file
   * system.
   * 
   * @param filePath a valid <code>String</code> path to the file to validate.
   * 
   * @return <code>true</code> if and only if the <code>java.io.File</code>
   *         instance represented by the <code>filePath</code> parameter
   *         represents an existent file.
   */
  public boolean exists(final String filePath) {
    if (StringUtils.getInstance().isBlank(filePath)) {
      throw new IllegalArgumentException("Invalid file path.");
    }

    return exists(new File(filePath));
  }
}
