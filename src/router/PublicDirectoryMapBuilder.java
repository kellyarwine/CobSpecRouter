package router;

import builder.routeType.Directory;
import builder.routeType.Public;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PublicDirectoryMapBuilder {
  private File fileDirectoryTemplate;
  private HashMap publicDirectoryMap;

  public HashMap get(File publicDirectoryFullPath) throws IOException {
    fileDirectoryTemplate = new File(publicDirectoryFullPath, "templates/file_directory.html");
    publicDirectoryMap = new HashMap();
    populatePublicDirectoryMap(publicDirectoryFullPath, publicDirectoryFullPath.toString());
    addRootDirectory(publicDirectoryFullPath);
    return publicDirectoryMap;
  }

  private void populatePublicDirectoryMap(File publicDirectoryFullPath, String directoryName){
    File directory = new File(directoryName);
    File[] fileList = directory.listFiles();
    for (File file : fileList){
      getRouteAndRouteInfo(publicDirectoryFullPath, file);
    }
  }

  private void getRouteAndRouteInfo(File publicDirectoryFullPath, File file) {
    if (file.isDirectory()) {
      String relativeFileAndPath = subtractPath(file.getAbsolutePath(), publicDirectoryFullPath);
      ArrayList routeInfo = createDirectoryRouteInfo(publicDirectoryFullPath);
      publicDirectoryMap.put(relativeFileAndPath, routeInfo);
      populatePublicDirectoryMap(publicDirectoryFullPath, file.getAbsolutePath());
    }
    else if (file.isFile()){
      String relativeFileAndPath = subtractPath(file.getAbsolutePath(), publicDirectoryFullPath);
      ArrayList routeInfo = createFileRouteInfo(file);
      publicDirectoryMap.put(relativeFileAndPath, routeInfo);
    }
  }

  private ArrayList createFileRouteInfo(File file) {
    ArrayList routeInfo = new ArrayList();
    routeInfo.add(file);
    routeInfo.add(new Public());
    return routeInfo;
  }

  private ArrayList createDirectoryRouteInfo(File publicDirectoryFullPath) {
    ArrayList routeInfo = new ArrayList();
    routeInfo.add(fileDirectoryTemplate);
    routeInfo.add(new Directory(publicDirectoryFullPath));
    return routeInfo;
  }

  private void addRootDirectory(File publicDirectoryFullPath) {
    publicDirectoryMap.put("/", createDirectoryRouteInfo(publicDirectoryFullPath));
  }

  private String subtractPath(String filePath, File publicDirectoryFullPath) {
    return filePath.replace(publicDirectoryFullPath.toString(), "");
  }
}