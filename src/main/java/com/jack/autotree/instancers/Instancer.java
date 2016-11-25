package com.jack.autotree.instancers;

public class Instancer
{
  public static Object istantiate(Class<?> clazz)
  {
    return istantiatePrimitive(clazz);
  }
  
  private static Object istantiatePrimitive(Class<?> clazz)
  {
    if (clazz == Integer.class)
      return new Integer(0);
    else if (clazz == Float.class)
      return new Float(0.0f);
    else if (clazz == Double.class)
      return new Double(0.0);
    else return null;
  }
}
