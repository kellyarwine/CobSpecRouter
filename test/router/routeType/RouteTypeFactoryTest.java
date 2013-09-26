package router.routeType;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class RouteTypeFactoryTest {
  File publicDirectoryFullPath;
  RouteTypeFactory routeTypeFactory;

  @Before
  public void setUp() {
    File workingDirectory = new File(System.getProperty("user.dir"));
    publicDirectoryFullPath = new File(workingDirectory, "test/public/");
    routeTypeFactory = new RouteTypeFactory();
  }

  @Test
  public void directory() throws IOException {
    Matcher expectedResult = instanceOf(Directory.class);
    RouteType actualResult = routeTypeFactory.build("directory", new File(publicDirectoryFullPath, "/images"));
    assertThat(actualResult, expectedResult);
  }

  @Test
  public void fileNotFound() throws IOException {
    Matcher expectedResult = instanceOf(FileNotFound.class);
    RouteType actualResult = routeTypeFactory.build("filenotfound", new File(publicDirectoryFullPath, "/images"));
    assertThat(actualResult, expectedResult);
  }

  @Test
  public void publicClass() throws IOException {
    Matcher expectedResult = instanceOf(Public.class);
    RouteType actualResult = routeTypeFactory.build("public", new File(publicDirectoryFullPath, "/images"));
    assertThat(actualResult, expectedResult);
  }

  @Test
  public void redirect() throws IOException {
    Matcher expectedResult = instanceOf(Redirect.class);
    RouteType actualResult = routeTypeFactory.build("redirect", new File(publicDirectoryFullPath, "/images"));
    assertThat(actualResult, expectedResult);
  }
}