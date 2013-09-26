package router.csvMapBuilder;

import router.routeType.Public;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RouteMapBuilderTest {
  @Test
  public void buildsHashMap() throws IOException {
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    File routesFile = new File(workingDirectory, "test/routes.csv");
    File publicDirectoryFullPath = new File(workingDirectory, "/test/public/");
    RouteMapBuilder routeMapBuilder = new RouteMapBuilder(publicDirectoryFullPath);
    HashMap actualResult = routeMapBuilder.get(routesFile);
    ArrayList actualRouteInfo1 = (ArrayList)actualResult.get("/parameters");
    ArrayList actualRouteInfo2 = (ArrayList)actualResult.get("/rainbow");
    ArrayList actualRouteInfo3 = (ArrayList)actualResult.get("/punky");
    ArrayList actualRouteInfo4 = (ArrayList)actualResult.get("/my_little_pony");
    ArrayList actualRouteInfo5 = (ArrayList)actualResult.get("/celebrate");
    ArrayList actualRouteInfo6 = (ArrayList)actualResult.get("/form");

    HashMap expectedResult = new HashMap();
    ArrayList expectedRouteInfo1 = new ArrayList();
    expectedRouteInfo1.add(new File(publicDirectoryFullPath, "/templates/parameters.html"));
    expectedRouteInfo1.add(new Public());
    expectedResult.put("/parameters", expectedRouteInfo1);
    ArrayList expectedRouteInfo2 = new ArrayList();
    expectedRouteInfo2.add(new File(publicDirectoryFullPath, "/rainbow_brite.jpeg"));
    expectedRouteInfo2.add(new Public());
    expectedResult.put("/rainbow", expectedRouteInfo2);
    ArrayList expectedRouteInfo3 = new ArrayList();
    expectedRouteInfo3.add(new File(publicDirectoryFullPath, "/punky_brewster.jpg"));
    expectedRouteInfo3.add(new Public());
    expectedResult.put("/punky", expectedRouteInfo3);
    ArrayList expectedRouteInfo4 = new ArrayList();
    expectedRouteInfo4.add(new File(publicDirectoryFullPath, "/my_little_pony.png"));
    expectedRouteInfo4.add(new Public());
    expectedResult.put("/my_little_pony", expectedRouteInfo4);
    ArrayList expectedRouteInfo5 = new ArrayList();
    expectedRouteInfo5.add(new File(publicDirectoryFullPath, "/celebrate.gif"));
    expectedRouteInfo5.add(new Public());
    expectedResult.put("/celebrate", expectedRouteInfo5);
    ArrayList expectedRouteInfo6 = new ArrayList();
    expectedRouteInfo6.add(new File(publicDirectoryFullPath, "/templates/form.html"));
    expectedRouteInfo6.add(new Public());
    expectedResult.put("/form", expectedRouteInfo6);

    assertEquals(expectedRouteInfo1.get(0), actualRouteInfo1.get(0));
    assertEquals(expectedRouteInfo2.get(0), actualRouteInfo2.get(0));
    assertEquals(expectedRouteInfo3.get(0), actualRouteInfo3.get(0));
    assertEquals(expectedRouteInfo4.get(0), actualRouteInfo4.get(0));
    assertEquals(expectedRouteInfo5.get(0), actualRouteInfo5.get(0));
    assertEquals(expectedRouteInfo6.get(0), actualRouteInfo6.get(0));
    assertThat(actualRouteInfo1.get(1), instanceOf(Public.class));
    assertThat(actualRouteInfo2.get(1), instanceOf(Public.class));
    assertThat(actualRouteInfo3.get(1), instanceOf(Public.class));
    assertThat(actualRouteInfo4.get(1), instanceOf(Public.class));
    assertThat(actualRouteInfo5.get(1), instanceOf(Public.class));
    assertThat(actualRouteInfo6.get(1), instanceOf(Public.class));
  }
}
