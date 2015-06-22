package com.jack.autotree.proxies;

import java.lang.reflect.Field;
import com.jack.autotree.annotations.*;

public class FieldProxy implements ValueProxy
{
  private final Field field;
  private final Object parent;
  
  public FieldProxy(Object parent, Field field)
  {
    this.field = field;
    this.parent = parent;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T get()
  {
    try
    {
      return (T)field.get(parent);
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
      return null;
    }
  }
  
  public <T> void set(T value)
  {
    try
    {
      field.set(parent, value);
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
  
  public String mnemonic() { return field.getName(); }
  
  public boolean isEditable()
  {
    return field.getAnnotation(NonEditable.class) == null;
  }
}