package com.jack.autotree.builders;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class GenericType
{
  private Type rawType;
  private Type[] typeArguments;
  
  public GenericType(Class<?> type)
  {
    ParameterizedType pt = (ParameterizedType)type.getGenericSuperclass();
    rawType = pt.getRawType();
    typeArguments = pt.getActualTypeArguments();
  }
  
  public String toString()
  {
    return rawType + "<" +
        String.join(",", Arrays.stream(typeArguments).map(Object::toString).toArray(i -> new String[i])) + ">";
  }
}
