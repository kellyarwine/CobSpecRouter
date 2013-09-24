package router.csvMapBuilder;

import builder.routeType.Redirect;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class HtAccessMapBuilderTest {
  @Test
  public void buildsHashMap() throws IOException {
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    File htAccessFile = new File(workingDirectory, "test/.htaccess");
    File publicDirectoryFullPath = new File(workingDirectory, "/test/public/");
    HtAccessMapBuilder htAccessMapBuilder = new HtAccessMapBuilder(publicDirectoryFullPath);
    HashMap actualResult = htAccessMapBuilder.get(htAccessFile);
    ArrayList actualRouteInfo = (ArrayList)actualResult.get("/redirect");

    HashMap expectedResult = new HashMap();
    ArrayList expectedRouteInfo = new ArrayList();
    expectedRouteInfo.add(new File(publicDirectoryFullPath, "/"));
    expectedRouteInfo.add(new Redirect());
    expectedResult.put("/redirect", expectedRouteInfo);

    assertEquals(expectedRouteInfo.get(0), actualRouteInfo.get(0));
    assertThat(actualRouteInfo.get(1), instanceOf(Redirect.class));
  }
}