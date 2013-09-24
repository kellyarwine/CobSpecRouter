package router.csvMapBuilder;

import java.io.*;
import java.util.HashMap;

public abstract class CsvMapBuilder {
  public File publicDirectoryFullPath;

  public CsvMapBuilder(File publicDirectoryFullPath) {
    this.publicDirectoryFullPath = publicDirectoryFullPath;
  }

  public HashMap get(File csvFile) throws IOException {
    String csvText = toString(csvFile);
    String[] csvLines = parseLines(csvText);
    return createMap(csvLines);
  }

  private String toString(File csvFileName) throws IOException {
    InputStream inputStream = new FileInputStream(csvFileName);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int chr;
    while ((chr = inputStream.read()) != -1)
      outputStream.write(chr);
    return outputStream.toString();
  }

  private String[] parseLines(String csvData) {
    return csvData.split("\\r?\\n");
  }

  public abstract HashMap createMap(String[] csvLines);
}