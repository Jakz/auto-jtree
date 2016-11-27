package com.jack.autotree.types;

import java.lang.reflect.Type;

public class RawType implements AutoType
{
  private final Class<?> type;
  
  public RawType(Class<?> type) { this.type = type; }
  
  @Override public Class<?> get() { return type; }
  
  @Override public Object newInstance() throws IllegalAccessException, InstantiationException { return type.newInstance(); }
  
  //@Override public boolean equals(Object other) { return other instanceof RawType && ((RawType)other).type == type; }
  @Override public int hashCode() { return type.hashCode(); }
  @Override public boolean equals(Object o) { return o instanceof AutoType && get() == ((AutoType)o).get(); }

  @Override public String toString() { return type.getCanonicalName(); }
}
