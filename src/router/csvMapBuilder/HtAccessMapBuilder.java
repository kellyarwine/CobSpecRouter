package router.csvMapBuilder;

import router.routeType.Redirect;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class HtAccessMapBuilder extends CsvMapBuilder {
  private String CHARACTER_DELIMITER = " ";

  public HtAccessMapBuilder(File publicDirectoryFullPath) {
    super(publicDirectoryFullPath);
  }

  public HashMap createMap(String[] csvLines) {
    HashMap hashMap = new HashMap();
    for (int i=0; i<csvLines.length; i++) {
      String[] lineArray = csvLines[i].split(CHARACTER_DELIMITER);
      String url = lineArray[1].trim();
      String route = lineArray[2].trim();
      ArrayList routeInfo = createRouteInfo(route);
      hashMap.put(url, routeInfo);
    }
    return hashMap;
  }

  private ArrayList createRouteInfo(String route) {
    ArrayList routeInfo = new ArrayList();
    routeInfo.add(new File(publicDirectoryFullPath, route));
    routeInfo.add(new Redirect());
    return routeInfo;
  }
}