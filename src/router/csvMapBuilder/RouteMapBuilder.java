package router.csvMapBuilder;

import builder.routeType.RouteTypeFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class RouteMapBuilder extends CsvMapBuilder {
  private String CHARACTER_DELIMITER = ",";

  public RouteMapBuilder(File publicDirectoryFullPath) {
    super(publicDirectoryFullPath);
  }

  public HashMap createMap(String[] lines) {
    HashMap hashMap = new HashMap();
    for (int i=0; i<lines.length; i++) {
      String line = lines[i];
      String[] lineArray = line.split(CHARACTER_DELIMITER);
      String url = lineArray[0].trim();
      String route = lineArray[1].trim();
      String routeType = lineArray[2].trim();
      ArrayList routeInfo = createRouteInfo(route, routeType);
      hashMap.put(url, routeInfo);
    }
    return hashMap;
  }

  private ArrayList createRouteInfo(String route, String routeType) {
    ArrayList<Object> routeInfo = new ArrayList<Object>();
    File routeFile = new File(publicDirectoryFullPath, route);
    routeInfo.add(routeFile);
    routeInfo.add(new RouteTypeFactory().build(routeType, routeFile));
    return routeInfo;
  }
}