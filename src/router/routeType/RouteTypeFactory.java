package router.routeType;

import java.io.File;

public class RouteTypeFactory {
  public RouteType build(String routeType, File routeFile) {
    if (routeType.equalsIgnoreCase("directory"))
      return new Directory(routeFile);
    else if (routeType.equalsIgnoreCase("filenotfound"))
      return new FileNotFound();
    else if (routeType.equalsIgnoreCase("public"))
      return new Public();
    else
      return new Redirect();
  }
}