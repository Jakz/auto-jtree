package com.jack.autotree.proxies;

import java.lang.reflect.Field;
import com.jack.autotree.annotations.*;

public class FieldProxy extends ValueProxy
{
  private final Object object;
  private final Field field;
  
  public FieldProxy(ValueProxy parent, Object object, Field field)
  {
    super(parent);
    this.object = object;
    this.field = field;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T get()
  {
    try
    {
      return (T)field.get(object);
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
      field.set(object, value);
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
  
  public Class<?> getType() { return field.getType(); }
}