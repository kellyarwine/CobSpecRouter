package router;

import builder.Templater;
import router.routeType.Directory;
import router.routeType.FileNotFound;
import router.routeType.Public;
import router.routeType.Redirect;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class RouterMapBuilderTest {
  private DefaultHashMap actualResult;
  private File publicDirectoryFullPath;

  @Before
  public void setUp() throws IOException {
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    publicDirectoryFullPath = new File(workingDirectory, "test/public/");
    new Templater().copyTemplatesToDisk("/builder/templates/templates.zip", publicDirectoryFullPath);
    RouterMapBuilder routerMapBuilder = new RouterMapBuilder();
    actualResult = routerMapBuilder.buildFrom(getServerConfig(workingDirectory));
  }

  @After
  public void tearDown() {
    deleteDirectory(new File(publicDirectoryFullPath, "/templates"));
  }

  @Test
  public void one() {
    String route = "/images";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "templates/file_directory.html"));
    assertThat(actualArrayList.get(1), instanceOf(Directory.class));
  }

  @Test
  public void two() {
    String route = "/stylesheets";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "templates/file_directory.html"));
    assertThat(actualArrayList.get(1), instanceOf(Directory.class));
  }

  @Test
  public void three() {
    String route = "/templates";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "templates/file_directory.html"));
    assertThat(actualArrayList.get(1), instanceOf(Directory.class));
  }

  @Test
  public void four() {
    String route = "/test_directory";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "templates/file_directory.html"));
    assertThat(actualArrayList.get(1), instanceOf(Directory.class));
  }

  @Test
  public void five() {
    String route = "/celebrate.gif";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void six() {
    String route = "/color_picker.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void seven() {
    String route = "/color_picker_post.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void eight() {
    String route = "/color_picker_result.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void nine() {
    String route = "/favicon.ico";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void ten() {
    String route = "/favicon1.ico";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void eleven() {
    String route = "/hi_everyone.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twelve() {
    String route = "/index.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void thirteen() {
    String route = "/my_little_pony.png";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void fourteen() {
    String route = "/.DS_Store";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void fifteen() {
    String route = "/partial_content.txt";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void sixteen() {
    String route = "/punky_brewster.jpg";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void seventeen() {
    String route = "/rainbow_brite.jpeg";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void eighteen() {
    String route = "/the_goal.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void nineteen() {
    String route = "/the_goal.txt";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twenty() {
    String route = "/celebrate";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/celebrate.gif"));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twentyOne() {
    String route = "/my_little_pony";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/my_little_pony.png"));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twentyTwo() {
    String route = "/punky";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/punky_brewster.jpg"));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twentyThree() {
    String route = "/rainbow";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/rainbow_brite.jpeg"));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twentyFour() {
    String route = "/parameters";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/templates/parameters.html"));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twentyFive() {
    String route = "/form";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/templates/form.html"));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twentySix() {
    String route = "/redirect";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/"));
    assertThat(actualArrayList.get(1), instanceOf(Redirect.class));
  }

  @Test
  public void twentySeven() {
    String route = "/another_redirect";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/hi_everyone.html"));
    assertThat(actualArrayList.get(1), instanceOf(Redirect.class));
  }

  @Test
  public void twentyEight() {
    String route = "/images/404.png";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void twentyNine() {
    String route = "/images/another_404.png";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void thirty() {
    String route = "/stylesheets/stylesheet.css";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void thirtyOne() {
    String route = "/templates/404.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void thirtyTwo() {
    String route = "/templates/file_directory.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void thirtyThree() {
    String route = "/templates/form.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void thirtyFour() {
    String route = "/templates/parameters.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void thirtyFive() {
    String route = "/";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "templates/file_directory.html"));
    assertThat(actualArrayList.get(1), instanceOf(Directory.class));
  }

  @Test
  public void thirtySeven() {
    assertEquals(35, actualResult.size());
  }

  @Test
  public void returnsFourOhFourRoute() throws IOException {
    String route = "/this_url_does_not_exist";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/templates/404.html"));
    assertThat(actualArrayList.get(1), instanceOf(FileNotFound.class));
  }

  private ArrayList getRouterMapValue(String route) {
    return (ArrayList)actualResult.get(route);
  }

  public byte[] toBytes(File routeFile) throws IOException {
    InputStream inputStream = new FileInputStream(routeFile);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int chr;

    while ((chr = inputStream.read()) != -1)
      outputStream.write(chr);

    return outputStream.toByteArray();
  }

  public void deleteDirectory(File directory)
  {
    if (directory.isDirectory()) {
      String[] children = directory.list();
      for (int i=0; i<children.length; i++) {
        deleteDirectory(new File(directory, children[i]));
      }
    }
    directory.delete();
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