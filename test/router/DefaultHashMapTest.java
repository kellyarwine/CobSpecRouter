package router;

import builder.code.FourHundredFour;
import builder.code.TwoHundred;
import router.DefaultHashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class DefaultHashMapTest {

  private DefaultHashMap defaultHashMap;

  @Before
  public void setUp() {
    defaultHashMap = new DefaultHashMap(new ArrayList(Arrays.asList("/404.html", new FourHundredFour())));
    defaultHashMap.put("hi", new ArrayList(Arrays.asList("everyone", new TwoHundred())));
  }

  @Test
  public void keyExists() {
    ArrayList actualResult = (ArrayList)defaultHashMap.get("hi");
    assertEquals("everyone", actualResult.get(0));
    assertThat(actualResult.get(1), instanceOf(TwoHundred.class));
  }

  @Test
  public void keyDoesNotExist() {
    ArrayList actualResult = (ArrayList)defaultHashMap.get("this_key_does_not_exist");
    assertEquals("/404.html", actualResult.get(0));
    assertThat(actualResult.get(1), instanceOf(FourHundredFour.class));
  }

}