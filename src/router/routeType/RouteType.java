package router.routeType;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public interface RouteType {
  public byte[] buildResponse(File routeFile, HashMap request) throws IOException, ParseException;
}
