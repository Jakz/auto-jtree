package com.jack.autotree.instancers;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

public abstract class Instancer
{
  public Instancer() { }
    
  abstract public Object instantiate(Class<?> clazz);
  
  public static Instancer PRIMITIVE_INSTANCER = new Instancer() {
    @Override public Object instantiate(Class<?> clazz)
    {
      if (clazz == Byte.class || clazz == Byte.TYPE)
        return new Byte((byte)0);
      else if (clazz == Character.class || clazz == Character.TYPE)
        return new Character((char)0);
      else if (clazz == Short.class || clazz == Short.TYPE)
        return new Short((short)0);
      else if (clazz == Integer.class || clazz == Integer.TYPE)
        return new Integer(0);
      else if (clazz == Long.class || clazz == Long.TYPE)
        return new Long(0L);
      else if (clazz == Float.class || clazz == Float.TYPE)
        return new Float(0.0f);
      else if (clazz == Double.class || clazz == Double.TYPE)
        return new Double(0.0);
      else if (clazz == Boolean.class || clazz == Boolean.TYPE)
        return new Boolean(false);
      else return null;
    }
  };
  
  public static Instancer ARRAY_INSTANCER = new Instancer() {
    @Override public Object instantiate(Class<?> clazz)
    {
      if (clazz.isArray())
        return Array.newInstance(clazz.getComponentType(), 0);
      
      return null;         
    }
  };
  
  public static Instancer DEFAULT_INSTANCER = new Instancer() {
    @Override public Object instantiate(Class<?> clazz)
    {
      try
      {
        return clazz.newInstance();
      }
      catch (Exception e)
      {
        e.printStackTrace();
        return null;
      }
    }
  };
}
