package com.jack.autotree.types;

import java.lang.reflect.Type;

public interface AutoType extends Type
{
  default boolean isRaw() { return true; }
  default boolean isSubtypeOf(AutoType superType) { return superType.get().isAssignableFrom(this.get()); }
  public Class<?> get();
  public Object newInstance() throws IllegalAccessException, InstantiationException;
}