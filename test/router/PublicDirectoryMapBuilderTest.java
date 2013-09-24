package router;

import builder.Templater;
import builder.routeType.Directory;
import builder.routeType.Public;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import router.PublicDirectoryMapBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PublicDirectoryMapBuilderTest {
  File publicDirectory;

  @Before
  public void setUp() throws IOException {
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    publicDirectory = new File(workingDirectory, "test/public/");
    new Templater().copyTemplatesToDisk("/builder/templates/templates.zip", publicDirectory);  }

  @After
  public void tearDown() {
    deleteDirectory(new File(publicDirectory, "/templates"));
  }

  @Test
  public void buildsHashMap() throws IOException {
    PublicDirectoryMapBuilder publicDirectoryMapBuilder = new PublicDirectoryMapBuilder();
    HashMap actualResult = publicDirectoryMapBuilder.get(publicDirectory);
    ArrayList actualRouteInfo1 = (ArrayList)actualResult.get("/images");
    ArrayList actualRouteInfo2 = (ArrayList)actualResult.get("/stylesheets");
    ArrayList actualRouteInfo3 = (ArrayList)actualResult.get("/templates");
    ArrayList actualRouteInfo4 = (ArrayList)actualResult.get("/test_directory");
    ArrayList actualRouteInfo5 = (ArrayList)actualResult.get("/celebrate.gif");
    ArrayList actualRouteInfo6 = (ArrayList)actualResult.get("/color_picker.html");
    ArrayList actualRouteInfo7 = (ArrayList)actualResult.get("/celebrate.gif");
    ArrayList actualRouteInfo8 = (ArrayList)actualResult.get("/color_picker_result.html");
    ArrayList actualRouteInfo9 = (ArrayList)actualResult.get("/favicon.ico");
    ArrayList actualRouteInfo10 = (ArrayList)actualResult.get("/favicon1.ico");
    ArrayList actualRouteInfo11 = (ArrayList)actualResult.get("/hi_everyone.html");
    ArrayList actualRouteInfo12 = (ArrayList)actualResult.get("/index.html");
    ArrayList actualRouteInfo13 = (ArrayList)actualResult.get("/my_little_pony.png");
    ArrayList actualRouteInfo14 = (ArrayList)actualResult.get("/.DS_Store");
    ArrayList actualRouteInfo15 = (ArrayList)actualResult.get("/punky_brewster.jpg");
    ArrayList actualRouteInfo16 = (ArrayList)actualResult.get("/rainbow_brite.jpeg");
    ArrayList actualRouteInfo17 = (ArrayList)actualResult.get("/the_goal.html");
    ArrayList actualRouteInfo18 = (ArrayList)actualResult.get("/the_goal.txt");
    ArrayList actualRouteInfo19 = (ArrayList)actualResult.get("/images/404.png");
    ArrayList actualRouteInfo20 = (ArrayList)actualResult.get("/stylesheets/stylesheet.css");
    ArrayList actualRouteInfo21 = (ArrayList)actualResult.get("/templates/404.html");
    ArrayList actualRouteInfo22 = (ArrayList)actualResult.get("/templates/file_directory.html");
    ArrayList actualRouteInfo23 = (ArrayList)actualResult.get("/templates/form.html");
    ArrayList actualRouteInfo24 = (ArrayList)actualResult.get("/templates/parameters.html");
    ArrayList actualRouteInfo25 = (ArrayList)actualResult.get("/partial_content.txt");
    ArrayList actualRouteInfo26 = (ArrayList)actualResult.get("/templates/file_directory.html");
    ArrayList actualRouteInfo27 = (ArrayList)actualResult.get("/images/another_404.png");
    ArrayList actualRouteInfo28 = (ArrayList)actualResult.get("/");

    HashMap expectedResult = new HashMap();
    ArrayList expectedRouteInfo1 = new ArrayList();
    expectedRouteInfo1.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo1.add(new Directory(publicDirectory));
    expectedResult.put("/images", expectedRouteInfo1);
    ArrayList expectedRouteInfo2 = new ArrayList();
    expectedRouteInfo2.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo2.add(new Directory(publicDirectory));
    expectedResult.put("/stylesheets", expectedRouteInfo2);
    ArrayList expectedRouteInfo3 = new ArrayList();
    expectedRouteInfo3.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo3.add(new Directory(publicDirectory));
    expectedResult.put("/templates", expectedRouteInfo3);
    ArrayList expectedRouteInfo4 = new ArrayList();
    expectedRouteInfo4.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo4.add(new Directory(publicDirectory));
    expectedResult.put("/test_directory", expectedRouteInfo4);
    ArrayList expectedRouteInfo5 = new ArrayList();
    expectedRouteInfo5.add(new File(publicDirectory, "/celebrate.gif"));
    expectedRouteInfo5.add(new Public());
    expectedResult.put("/celebrate.gif", expectedRouteInfo5);
    ArrayList expectedRouteInfo6 = new ArrayList();
    expectedRouteInfo6.add(new File(publicDirectory, "/color_picker.html"));
    expectedRouteInfo6.add(new Public());
    expectedResult.put("/color_picker.html", expectedRouteInfo6);
    ArrayList expectedRouteInfo7 = new ArrayList();
    expectedRouteInfo7.add(new File(publicDirectory, "/celebrate.gif"));
    expectedRouteInfo7.add(new Public());
    expectedResult.put("/color_picker_post.html", expectedRouteInfo7);
    ArrayList expectedRouteInfo8 = new ArrayList();
    expectedRouteInfo8.add(new File(publicDirectory, "/color_picker_result.html"));
    expectedRouteInfo8.add(new Public());
    expectedResult.put("/color_picker_result.html", expectedRouteInfo8);
    ArrayList expectedRouteInfo9 = new ArrayList();
    expectedRouteInfo9.add(new File(publicDirectory, "/favicon.ico"));
    expectedRouteInfo9.add(new Public());
    expectedResult.put("/favicon.ico", expectedRouteInfo9);
    ArrayList expectedRouteInfo10 = new ArrayList();
    expectedRouteInfo10.add(new File(publicDirectory, "/favicon1.ico"));
    expectedRouteInfo10.add(new Public());
    expectedResult.put("/favicon1.ico", expectedRouteInfo10);
    ArrayList expectedRouteInfo11 = new ArrayList();
    expectedRouteInfo11.add(new File(publicDirectory, "/hi_everyone.html"));
    expectedRouteInfo11.add(new Public());
    expectedResult.put("/hi_everyone.html", expectedRouteInfo11);
    ArrayList expectedRouteInfo12 = new ArrayList();
    expectedRouteInfo12.add(new File(publicDirectory, "/index.html"));
    expectedRouteInfo12.add(new Public());
    expectedResult.put("/index.html", expectedRouteInfo12);
    ArrayList expectedRouteInfo13 = new ArrayList();
    expectedRouteInfo13.add(new File(publicDirectory, "/my_little_pony.png"));
    expectedRouteInfo13.add(new Public());
    expectedResult.put("/my_little_pony.png", expectedRouteInfo13);
    ArrayList expectedRouteInfo14 = new ArrayList();
    expectedRouteInfo14.add(new File(publicDirectory, "/.DS_Store"));
    expectedRouteInfo14.add(new Public());
    expectedResult.put("/.DS_Store", expectedRouteInfo14);
    ArrayList expectedRouteInfo15 = new ArrayList();
    expectedRouteInfo15.add(new File(publicDirectory, "/punky_brewster.jpg"));
    expectedRouteInfo15.add(new Public());
    expectedResult.put("/punky_brewster.jpg", expectedRouteInfo15);
    ArrayList expectedRouteInfo16 = new ArrayList();
    expectedRouteInfo16.add(new File(publicDirectory, "/rainbow_brite.jpeg"));
    expectedRouteInfo16.add(new Public());
    expectedResult.put("/rainbow_brite.jpeg", expectedRouteInfo16);
    ArrayList expectedRouteInfo17 = new ArrayList();
    expectedRouteInfo17.add(new File(publicDirectory, "/the_goal.html"));
    expectedRouteInfo17.add(new Public());
    expectedResult.put("/the_goal.html", expectedRouteInfo17);
    ArrayList expectedRouteInfo18 = new ArrayList();
    expectedRouteInfo18.add(new File(publicDirectory, "/the_goal.txt"));
    expectedRouteInfo18.add(new Public());
    expectedResult.put("/the_goal.txt", expectedRouteInfo18);
    ArrayList expectedRouteInfo19 = new ArrayList();
    expectedRouteInfo19.add(new File(publicDirectory, "/images/404.png"));
    expectedRouteInfo19.add(new Public());
    expectedResult.put("/images/404.png", expectedRouteInfo19);
    ArrayList expectedRouteInfo20 = new ArrayList();
    expectedRouteInfo20.add(new File(publicDirectory, "/stylesheets/stylesheet.css"));
    expectedRouteInfo20.add(new Public());
    expectedResult.put("/stylesheets/stylesheet.css", expectedRouteInfo20);
    ArrayList expectedRouteInfo21 = new ArrayList();
    expectedRouteInfo21.add(new File(publicDirectory, "/templates/404.html"));
    expectedRouteInfo21.add(new Public());
    expectedResult.put("/templates/404.html", expectedRouteInfo21);
    ArrayList expectedRouteInfo22 = new ArrayList();
    expectedRouteInfo22.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo22.add(new Public());
    expectedResult.put("/templates/file_directory.html", expectedRouteInfo22);
    ArrayList expectedRouteInfo23 = new ArrayList();
    expectedRouteInfo23.add(new File(publicDirectory, "/templates/form.html"));
    expectedRouteInfo23.add(new Public());
    expectedResult.put("/templates/form.html", expectedRouteInfo23);
    ArrayList expectedRouteInfo24 = new ArrayList();
    expectedRouteInfo24.add(new File(publicDirectory, "/templates/parameters.html"));
    expectedRouteInfo24.add(new Public());
    expectedResult.put("/templates/parameters.html", expectedRouteInfo24);
    ArrayList expectedRouteInfo25 = new ArrayList();
    expectedRouteInfo25.add(new File(publicDirectory, "/partial_content.txt"));
    expectedRouteInfo25.add(new Public());
    expectedResult.put("/partial_content.txt", expectedRouteInfo25);
    ArrayList expectedRouteInfo26 = new ArrayList();
    expectedRouteInfo26.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo26.add(new Public());
    expectedResult.put("/templates/file_directory.html", expectedRouteInfo26);
    ArrayList expectedRouteInfo27 = new ArrayList();
    expectedRouteInfo27.add(new File(publicDirectory, "/images/another_404.png"));
    expectedRouteInfo27.add(new Public());
    expectedResult.put("/images/another_404.png", expectedRouteInfo27);
    ArrayList expectedRouteInfo28 = new ArrayList();
    expectedRouteInfo28.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo28.add(new Directory(publicDirectory));
    expectedResult.put("/", expectedRouteInfo28);

    assertEquals(expectedRouteInfo1.get(0), actualRouteInfo1.get(0));
    assertThat(actualRouteInfo1.get(1), instanceOf(Directory.class));
    assertEquals(expectedRouteInfo2.get(0), actualRouteInfo2.get(0));
    assertThat(actualRouteInfo2.get(1), instanceOf(Directory.class));
    assertEquals(expectedRouteInfo3.get(0), actualRouteInfo3.get(0));
    assertThat(actualRouteInfo3.get(1), instanceOf(Directory.class));
    assertEquals(expectedRouteInfo4.get(0), actualRouteInfo4.get(0));
    assertThat(actualRouteInfo4.get(1), instanceOf(Directory.class));
    assertEquals(expectedRouteInfo5.get(0), actualRouteInfo5.get(0));
    assertThat(actualRouteInfo5.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo6.get(0), actualRouteInfo6.get(0));
    assertThat(actualRouteInfo6.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo7.get(0), actualRouteInfo7.get(0));
    assertThat(actualRouteInfo7.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo8.get(0), actualRouteInfo8.get(0));
    assertThat(actualRouteInfo8.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo9.get(0), actualRouteInfo9.get(0));
    assertThat(actualRouteInfo9.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo10.get(0), actualRouteInfo10.get(0));
    assertThat(actualRouteInfo10.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo11.get(0), actualRouteInfo11.get(0));
    assertThat(actualRouteInfo11.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo12.get(0), actualRouteInfo12.get(0));
    assertThat(actualRouteInfo12.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo13.get(0), actualRouteInfo13.get(0));
    assertThat(actualRouteInfo13.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo14.get(0), actualRouteInfo14.get(0));
    assertThat(actualRouteInfo14.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo15.get(0), actualRouteInfo15.get(0));
    assertThat(actualRouteInfo15.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo16.get(0), actualRouteInfo16.get(0));
    assertThat(actualRouteInfo16.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo17.get(0), actualRouteInfo17.get(0));
    assertThat(actualRouteInfo17.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo18.get(0), actualRouteInfo18.get(0));
    assertThat(actualRouteInfo18.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo19.get(0), actualRouteInfo19.get(0));
    assertThat(actualRouteInfo19.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo20.get(0), actualRouteInfo20.get(0));
    assertThat(actualRouteInfo20.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo21.get(0), actualRouteInfo21.get(0));
    assertThat(actualRouteInfo21.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo22.get(0), actualRouteInfo22.get(0));
    assertThat(actualRouteInfo22.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo23.get(0), actualRouteInfo23.get(0));
    assertThat(actualRouteInfo23.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo24.get(0), actualRouteInfo24.get(0));
    assertThat(actualRouteInfo24.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo25.get(0), actualRouteInfo25.get(0));
    assertThat(actualRouteInfo25.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo26.get(0), actualRouteInfo26.get(0));
    assertThat(actualRouteInfo26.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo27.get(0), actualRouteInfo27.get(0));
    assertThat(actualRouteInfo27.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo28.get(0), actualRouteInfo28.get(0));
    assertThat(actualRouteInfo28.get(1), instanceOf(Directory.class));
  }

  private void deleteDirectory(File directory) {
    if (directory.isDirectory()) {
      String[] children = directory.list();
      for (int i=0; i<children.length; i++) {
        deleteDirectory(new File(directory, children[i]));
      }
    }
    directory.delete();
  }
}