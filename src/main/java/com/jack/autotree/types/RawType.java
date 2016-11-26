package com.jack.autotree.types;

import java.lang.reflect.Type;

public class RawType implements AutoType
{
  private final Class<?> type;
  
  RawType(Class<?> type) { this.type = type; }
  
  @Override public Class<?> get() { return type; }
  
  @Override public Object newInstance() throws IllegalAccessException, InstantiationException { return type.newInstance(); }
}
