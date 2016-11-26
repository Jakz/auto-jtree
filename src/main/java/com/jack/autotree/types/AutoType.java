package com.jack.autotree.types;

import java.lang.reflect.Type;

public interface AutoType extends Type
{
  default boolean isRaw() { return true; }
  public Class<?> get();
  public Object newInstance() throws IllegalAccessException, InstantiationException;
}