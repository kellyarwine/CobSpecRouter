package router;

import router.routeType.RouteType;
import http.SystemRouter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class Router implements SystemRouter {
  private DefaultHashMap routerMap;
  private File routeFile;

  public void getRouterMap(HashMap<String, String> serverConfig) throws IOException {
    routerMap = new RouterMapBuilder().buildFrom(serverConfig);
  }

  public byte[] getResponse(HashMap request) throws IOException, ParseException {
    ArrayList routeInfo = getRouteInfo(request);
    return buildResponse(request, routeInfo);
  }

  public String getRoute(HashMap request) throws IOException, ParseException {
    ArrayList routeInfo = getRouteInfo(request);
    return routeInfo.get(0).toString();
  }

  private byte[] buildResponse(HashMap request, ArrayList routeInfo) throws IOException, ParseException {
    routeFile = (File)routeInfo.get(0);
    RouteType routeType = (RouteType)routeInfo.get(1);
    return routeType.buildResponse(routeFile, request);
  }

  private ArrayList getRouteInfo(HashMap request) {
    String url = (String)request.get("url");
    return (ArrayList)routerMap.get(url);
  }

}