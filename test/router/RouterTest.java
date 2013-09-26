package router;

import builder.Templater;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RouterTest {

  private File publicDirectory;
  private Router router;

  @Before
  public void setUp() throws IOException {
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    publicDirectory = new File(workingDirectory, "test/public/");
    router = new Router();
    router.getRouterMap(getServerConfig(workingDirectory));
    new Templater().copyTemplatesToDisk("/builder/templates/templates.zip", publicDirectory);
  }

  @After
  public void tearDown() {
    deleteDirectory(new File(publicDirectory, "/templates"));
  }

  @Test
  public void publicRoute() throws IOException, ParseException {
    HashMap request = getRequestWithUrl("/the_goal.html");

    String actualRoute = router.getRoute(request);
    String expectedRoute = new File(publicDirectory, "the_goal.html").toString();
    assertEquals(expectedRoute, actualRoute);

    byte[] actualResponse = router.getResponse(request);
    byte[] expectedResponse = getExpectedResponseFromRoute("200 OK", "21552", new File(publicDirectory, "the_goal.html"));
    assertArrayEquals(expectedResponse, actualResponse);
  }

  @Test
  public void directoryRoute() throws IOException, ParseException {
    HashMap request = getRequestWithUrl("/");

    String actualRoute = router.getRoute(request);
    String expectedRoute = new File(publicDirectory, "templates/file_directory.html").toString();
    assertEquals(expectedRoute, actualRoute);

    byte[] actualResponse = router.getResponse(request);
    byte[] expectedResponse = getExpectedFileDirectoryResponse();
    assertArrayEquals(expectedResponse, actualResponse);
  }

  @Test
  public void fileNotFoundRoute() throws IOException, ParseException {
    HashMap request = getRequestWithUrl("/this_route_does_not_exist");

    String actualRoute = router.getRoute(request);
    String expectedRoute = new File(publicDirectory, "templates/404.html").toString();
    assertEquals(expectedRoute, actualRoute);

    byte[] actualResponse = router.getResponse(request);
    byte[] expectedResponse = getExpectedFileNotFoundResponse();
    assertArrayEquals(expectedResponse, actualResponse);
  }

  @Test
  public void redirectRoute() throws IOException, ParseException {
    HashMap request = getRequestWithUrl("/another_redirect");

    String actualRoute = router.getRoute(request);
    String expectedRoute = new File(publicDirectory, "hi_everyone.html").toString();
    assertEquals(expectedRoute, actualRoute);

    byte[] actualResponse = router.getResponse(request);
    byte[] expectedResponse = getExpectedRedirectResponse();
    assertArrayEquals(expectedResponse, actualResponse);
  }

  public void deleteDirectory(File directory) {
    if (directory.isDirectory()) {
      String[] children = directory.list();
      for (int i=0; i<children.length; i++) {
        deleteDirectory(new File(directory, children[i]));
      }
    }
    directory.delete();
  }

  private HashMap getRequestWithUrl(String url) {
    HashMap request = new HashMap();
    request.put("httpMethod", "GET");
    request.put("url", url);
    request.put("httpProtocol", "HTTP/1.1");
    request.put("Host", "localhost:5000");
    request.put("Connection", "keep-alive");
    request.put("Content-Length", "15");
    request.put("Cache-Control", "max-age=0");
    request.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
    request.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36");
    request.put("Accept-Encoding", "gzip,deflate,sdch");
    request.put("Accept-Language", "en-US,en;q=0.8");
    request.put("Cookie", "textwrapon=false; wysiwyg=textarea");
    request.put("queryString", "text_color=blue");
    return request;
  }

  private byte[] getExpectedHeader(String responseCode, String contentLength) throws ParseException {
    String expectedHeaderString = "HTTP/1.1 " + responseCode + "\r\n"
        + "Date: " + currentDateTime() + "\r\n"
        + "Server: NinjaServer 1.0\r\n"
        + "Content-type: text/html; charset=UTF-8\r\n"
        + "Content-length: " + contentLength + "\r\n";
    return expectedHeaderString.getBytes();
  }

  private byte[] concatenate(byte[][] byteArray) throws IOException {
    ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
    for(int i=0; i<byteArray.length; i++) {
      bOutput.write(byteArray[i]);
    }
    return bOutput.toByteArray();
  }

  private String currentDateTime() throws ParseException {
    Date unformattedDateTime = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    return sdf.format(unformattedDateTime);
  }

  private byte[] getExpectedResponseFromRoute(String responseCode, String contentLength, File route) throws ParseException, IOException {
    byte[] expectedHeader = getExpectedHeader(responseCode, contentLength);
    byte[] expectedBody = toBytes(route);
    return concatenate(new byte[][]{expectedHeader, "\r\n".getBytes(), expectedBody});
  }

  private byte[] toBytes(File routeFile) throws IOException {
    InputStream inputStream = new FileInputStream(routeFile);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int chr;

    while ((chr = inputStream.read()) != -1)
      outputStream.write(chr);

    return outputStream.toByteArray();
  }

  private byte[] getExpectedFileDirectoryResponse() throws ParseException {
    String expectedHeader = new String(getExpectedHeader("200 OK", "1049"));
    String expectedBody = getExpectedDirectoryBody();
    return (expectedHeader + "\r\n" + expectedBody).getBytes();
  }

  private String getExpectedDirectoryBody() {
    return "<HTML>\n"
        + "  <HEAD>\n"
        + "    <TITLE>\n"
        + "      File Directory\n"
        + "    </TITLE>\n"
        + "  </HEAD>\n"
        + "  <BODY>\n"
        + "    <H1>/ Folder</H1>\n"
        + "    <a href=\"/.DS_Store\">.DS_Store</a><br>"
        + "<a href=\"/celebrate.gif\">celebrate.gif</a><br>"
        + "<a href=\"/color_picker.html\">color_picker.html</a><br>"
        + "<a href=\"/color_picker_post.html\">color_picker_post.html</a><br>"
        + "<a href=\"/color_picker_result.html\">color_picker_result.html</a><br>"
        + "<a href=\"/favicon.ico\">favicon.ico</a><br>"
        + "<a href=\"/favicon1.ico\">favicon1.ico</a><br>"
        + "<a href=\"/hi_everyone.html\">hi_everyone.html</a><br>"
        + "<a href=\"/images\">images</a><br>"
        + "<a href=\"/index.html\">index.html</a><br>"
        + "<a href=\"/my_little_pony.png\">my_little_pony.png</a><br>"
        + "<a href=\"/partial_content.txt\">partial_content.txt</a><br>"
        + "<a href=\"/punky_brewster.jpg\">punky_brewster.jpg</a><br>"
        + "<a href=\"/rainbow_brite.jpeg\">rainbow_brite.jpeg</a><br>"
        + "<a href=\"/stylesheets\">stylesheets</a><br>"
        + "<a href=\"/templates\">templates</a><br>"
        + "<a href=\"/test_directory\">test_directory</a><br>"
        + "<a href=\"/the_goal.html\">the_goal.html</a><br>"
        + "<a href=\"/the_goal.txt\">the_goal.txt</a><br>\n"
        + "  </BODY>\n"
        + "</HTML>";
  }

  private byte[] getExpectedFileNotFoundResponse() throws ParseException {
    String expectedHeader = new String(getExpectedHeader("404 File Not Found", "127"));
    String expectedBody = getExpectedFileNotFoundBody();
    return (expectedHeader + "\r\n" + expectedBody).getBytes();
  }

  private String getExpectedFileNotFoundBody() {
    return
        "<html>\n" +
        "<head>\n" +
        "    <title>Page not found</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "  Your page cannot be found.  Please try again.\n" +
        "</body>\n" +
        "</html>\n";
  }

  private byte[] getExpectedRedirectResponse() throws ParseException {
    return
         ("HTTP/1.1 301 Moved Permanently\r\n"
        + "Date: " + currentDateTime() + "\r\n"
        + "Server: NinjaServer 1.0\r\n"
        + "Content-type: text/html; charset=UTF-8\r\n"
        + "Content-length: 0\r\n"
        + "Location: http://localhost:5000/hi_everyone.html\r\n\r\n").getBytes();
  }

  private HashMap getServerConfig(File workingDirectory) {
    HashMap<String, String> serverConfig = new HashMap<String, String>();
    serverConfig.put("port", "5000");
    serverConfig.put("publicDirectoryPath", "test/public/");
    serverConfig.put("env", "production");
    serverConfig.put("routesFilePath", "test/routes.csv");
    serverConfig.put("htAccessFilePath", "test/.htaccess");
    serverConfig.put("workingDirectoryPath", workingDirectory.toString());
    serverConfig.put("mockRequestsFilePath", "");
    return serverConfig;
  }
}