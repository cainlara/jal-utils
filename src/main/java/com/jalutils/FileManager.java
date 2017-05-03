package com.jalutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Handles file system operations. This class is implemented to be used as a
 * Singleton Pattern.
 * 
 * @author larajo7
 */
public final class FileManager {

  private static FileManager instance;

  private FileManager() {
    // hide constructor
  }

  /**
   * Appends the specified extension to the file name.
   * <p>
   * If the file's name does not end with the the extension specified by param
   * <code>extension</code>, then the extension is appended.
   * 
   * @param fileName
   *          the current file name.
   * @param extension
   *          the extension to append.
   * @return the fixed file's name ending with the extension specification.
   */
  public String addExtension(final String fileName, final String extension) {
    if (!StringUtils.getInstance().isValid(fileName)) {
      throw new IllegalArgumentException("FileName can not be null nor empty.");
    }

    String fixedFileName = fileName;

    if (!fixedFileName.endsWith(extension)) {
      fixedFileName += extension;
    }

    return fixedFileName;
  }

  public void copyFile(final File sourceFile, final File targetFile) throws IOException {
    if (sourceFile == null || !sourceFile.exists()) {
      throw new IOException("Source file (" + sourceFile.getAbsolutePath() + ") does not exist.");
    }

    if (targetFile == null) {
      throw new IOException("Target file can not be null.");
    }

    Path source = sourceFile.toPath();
    Path target = targetFile.toPath();

    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
  }

  /**
   * Deletes a file from the filesystem.
   * 
   * @param file2delete
   *          a <code>File</code> object that represents the file to delete.
   * @return <code>true</code> if and only if the file is successfully deleted;
   *         <code>false</code> otherwise
   * @throws IOException
   *           if the file does not exist in the file system or if the param
   *           <code>file2delete</code> represents a directory.
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

    return file2delete.delete();
  }

  /**
   * Deletes a file from the filesystem.
   * 
   * @param fileAbsolutePath
   *          the absolute path to the directory to delete.
   * @return <code>true</code> if and only if the file is successfully deleted;
   *         <code>false</code> otherwise
   * @throws IOException
   *           if the file does not exist in the filesystem or if the param
   *           <code>fileAbsolutePath</code> represents a directory.
   */
  public boolean deleteFile(final String fileAbsolutePath) throws IOException {
    if (!StringUtils.getInstance().isValid(fileAbsolutePath)) {
      throw new IllegalArgumentException("Invalid file path");
    }

    File file2delete = new File(fileAbsolutePath);
    return deleteFile(file2delete);
  }

  /**
   * Deletes a directory and its content from the filesystem.
   * 
   * @param folder2Delete
   *          a <code>File</code> object that represents the directory to
   *          delete.
   * @return <code>true</code> if and only if the directory is successfully
   *         deleted; <code>false</code> otherwise
   * @throws IOException
   *           if the directory does not exist in the file system, if the param
   *           <code>folderAbsolutePath</code> represents a file, or if any
   *           element from the directory's content can not be deleted.
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

    if (folder2Delete.listFiles().length == 0) {
      return folder2Delete.delete();
    }

    for (File file : folder2Delete.listFiles()) {
      if (file.isDirectory()) {
        if (!deleteFolder(file)) {
          throw new IOException("Impossible to delete folder " + file.getAbsolutePath());
        }
      } else if (file.isFile()) {
        if (!deleteFile(file)) {
          throw new IOException("Impossible to delete file " + file.getAbsolutePath());
        }
      }
    }

    return folder2Delete.delete();
  }

  /**
   * Deletes a directory and its content from the filesystem.
   * 
   * @param folderAbsolutePath
   *          the absolute path to the directory to delete.
   * @return <code>true</code> if and only if the directory is successfully
   *         deleted; <code>false</code> otherwise
   * @throws IOException
   *           if the directory does not exist in the file system or if the
   *           param <code>folderAbsolutePath</code> represents a file.
   */
  public boolean deleteFolder(final String folderAbsolutePath) throws IOException {
    if (!StringUtils.getInstance().isValid(folderAbsolutePath)) {
      throw new IllegalArgumentException("Invalid directory path");
    }

    File folder2Delete = new File(folderAbsolutePath);
    return deleteFolder(folder2Delete);
  }

  public String getFileExtension(final File file) {
    if (file == null) {
      throw new IllegalArgumentException("File can not be null");
    }

    String filePath = file.getAbsolutePath();

    return getFileExtension(filePath);
  }

  public String getFileExtension(final String filePath) {
    String extension = null;

    if (!StringUtils.getInstance().isValid(filePath)) {
      throw new IllegalArgumentException("File path is invalid");
    }

    if (filePath.indexOf(".") < 0) {
      throw new IllegalArgumentException("File pathdoes not contain dot character");
    }

    extension = filePath.substring(filePath.lastIndexOf(".") + 1);

    return extension;
  }

  /**
   * Retrives the only instance of this class.
   * 
   * @return an instance of this class.
   */
  public static FileManager getInstance() {
    if (instance == null) {
      instance = new FileManager();
    }

    return instance;
  }

  public File getParentFolder(final File file) {
    if (file == null) {
      throw new IllegalArgumentException("File can not be null");
    }

    if (!file.exists()) {
      throw new IllegalArgumentException("File " + file.getAbsolutePath() + " does not exist.");
    }

    return file.getParentFile();
  }

  public File getParentFolder(final String filePath) {
    if (!StringUtils.getInstance().isValid(filePath)) {
      throw new IllegalArgumentException("Invalid file path.");
    }

    File file = new File(filePath);

    return getParentFolder(file);
  }
}
