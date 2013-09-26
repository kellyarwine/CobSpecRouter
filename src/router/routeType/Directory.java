package router.routeType;

import builder.code.TwoHundred;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Directory implements RouteType {
  private File publicDirectoryFullPath;

  public Directory(File publicDirectoryFullPath) {
    this.publicDirectoryFullPath = publicDirectoryFullPath;
  }

  public byte[] buildResponse(File routeFile, HashMap request) throws IOException, ParseException {
    HashMap modifiedRequest = addDirectoryToQueryString(request);
    TwoHundred twoHundred = new TwoHundred();
    return twoHundred.build(routeFile, modifiedRequest);
  }

  private HashMap addDirectoryToQueryString(HashMap request) {
    String folderName = (String)request.get("url");
    String fileList = createFileList(new File(publicDirectoryFullPath, folderName), request);
    request.put("queryString", "folder_name=" + folderName + "&file_list=" + fileList);
    return request;
  }

  private String createFileList(File directory, HashMap request) {
    File[] fileList = directory.listFiles();
    StringBuilder stringBuilder = new StringBuilder();

    for(int i = 0; i < fileList.length; i++) {
      stringBuilder.append("<a href=\"");
      stringBuilder.append(getRootFolder((String) request.get("url")));
      stringBuilder.append("/" + fileList[i].getName());
      stringBuilder.append("\">");
      stringBuilder.append(fileList[i].getName());
      stringBuilder.append("</a><br>");
    }

    return stringBuilder.toString();
  }

  private String getRootFolder(String url) {
    if (url.equals("/"))
        return "";
    else
      return url;
  }
}