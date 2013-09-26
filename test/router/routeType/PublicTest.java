package router.routeType;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import static junit.framework.Assert.assertTrue;

public class PublicTest {
  @Test
  public void get() throws IOException, ParseException {
    HashMap request = new HashMap();
    request.put("httpMethod", "GET");
    request.put("url", "/hi_everyone.html");
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
    request.put("queryString", "Hi=Bye&everyone=someone");
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    File publicDirectoryFullPath = new File(workingDirectory, "test/public/");
    File routeFile = new File(publicDirectoryFullPath, (String)request.get("url"));
    Public publicClass = new Public();
    byte[] actualResultInBytes = publicClass.buildResponse(routeFile, request);
    String actualResult = new String(actualResultInBytes);

    String expectedResult  = "Bye, someone!";

    assertTrue(actualResult.contains(expectedResult));
  }

  @Test
  public void post() throws IOException, ParseException {
    HashMap request = new HashMap();
    request.put("httpMethod", "POST");
    request.put("url", "/hi_everyone.html");
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
    request.put("queryString", "Hi=Bye&everyone=someone");
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    File publicDirectoryFullPath = new File(workingDirectory, "test/public/");
    File routeFile = new File(publicDirectoryFullPath, (String)request.get("url"));
    Public publicClass = new Public();
    byte[] actualResultInBytes = publicClass.buildResponse(routeFile, request);
    String actualResult = new String(actualResultInBytes);

    String expectedResult  = "Bye, someone!";

    assertTrue(actualResult.contains(expectedResult));
  }

  @Test
  public void put() throws IOException, ParseException {
    HashMap request = new HashMap();
    request.put("httpMethod", "PUT");
    request.put("url", "/hi_everyone.html");
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
    request.put("queryString", "Hi=Bye&everyone=someone");
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    File publicDirectoryFullPath = new File(workingDirectory, "test/public/");
    File routeFile = new File(publicDirectoryFullPath, (String)request.get("url"));
    Public publicClass = new Public();
    byte[] actualResultInBytes = publicClass.buildResponse(routeFile, request);
    String actualResult = new String(actualResultInBytes);

    String expectedResult  = "Bye, someone!";

    assertTrue(actualResult.contains(expectedResult));
  }
}