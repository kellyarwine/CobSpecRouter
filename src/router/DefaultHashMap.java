package router;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultHashMap extends HashMap{
  private ArrayList defaultValues;

  public DefaultHashMap(ArrayList defaultValues){
    this.defaultValues = defaultValues;
  }

  public Object get(Object o){
    if(hasNoKey(o))
      return defaultValues;
    else
      return super.get(o);
  }

  private boolean hasNoKey(Object o) {
    return super.get(o) == null;
  }
}