package com.jack.autotree.types;

public class GenericType implements AutoType
{
  private final Class<?> rawType;
  private final AutoType[] genericTypes;
  
  GenericType(Class<?> rawType, AutoType... genericTypes)
  {
    this.rawType = rawType;
    this.genericTypes = genericTypes;
  }
  
  @Override public Class<?> get() { return rawType; }
  
  @Override public Object newInstance() throws IllegalAccessException, InstantiationException { return rawType.newInstance(); }

}
