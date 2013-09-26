package router;

import router.routeType.FileNotFound;
import router.csvMapBuilder.HtAccessMapBuilder;
import router.csvMapBuilder.RouteMapBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RouterMapBuilder {
  private File publicDirectoryFullPath;
  private DefaultHashMap routerMap;

  public DefaultHashMap buildFrom(HashMap<String, String> serverConfig) throws IOException {
    File workingDirectory = new File(serverConfig.get("workingDirectoryPath"));
    publicDirectoryFullPath = new File(workingDirectory, serverConfig.get("publicDirectoryPath"));
    File routesFile = new File(workingDirectory, serverConfig.get("routesFilePath"));
    File htAccessFile = new File(workingDirectory, serverConfig.get("htAccessFilePath"));
    routerMap = getDefaultHashMap();

    putPublicDirectoryFiles(publicDirectoryFullPath);
    putRoutes(routesFile);
    putRedirects(htAccessFile);

    return routerMap;
  }

  private DefaultHashMap getDefaultHashMap() throws IOException {
    ArrayList<Object> arrayList = new ArrayList<Object>();
    arrayList.add(new File(publicDirectoryFullPath, "/templates/404.html"));
    arrayList.add(new FileNotFound());
    return new DefaultHashMap(arrayList);
  }

  private void putPublicDirectoryFiles(File publicDirectoryFullPath) throws IOException {
    HashMap publicDirectoryMap = createPublicDirectoryMap(publicDirectoryFullPath);
    appendToRouterMap(publicDirectoryMap);
  }

  private void putRoutes(File routesFile) throws IOException {
    HashMap routesMap = createRoutesMap(routesFile);
    appendToRouterMap(routesMap);
  }

  private void putRedirects(File htAccessFile) throws IOException {
    HashMap htAccessMap = createHtaccessMap(htAccessFile);
    appendToRouterMap(htAccessMap);
  }

  private HashMap createPublicDirectoryMap(File publicDirectoryFullPath) throws IOException {
    PublicDirectoryMapBuilder publicDirectoryMapBuilder = new PublicDirectoryMapBuilder();
    return publicDirectoryMapBuilder.get(publicDirectoryFullPath);
  }

  private HashMap createRoutesMap(File routesFile) throws IOException {
    RouteMapBuilder routeMapBuilder = new RouteMapBuilder(publicDirectoryFullPath);
    return routeMapBuilder.get(routesFile);
  }

  private HashMap createHtaccessMap(File htAccessFile) throws IOException {
    HtAccessMapBuilder htAccessMapBuilder = new HtAccessMapBuilder(publicDirectoryFullPath);
    return htAccessMapBuilder.get(htAccessFile);
  }

  private void appendToRouterMap(HashMap map) {
    Iterator iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry entry = (Map.Entry)iterator.next();
      routerMap.put(entry.getKey(), entry.getValue());
    }
  }
}