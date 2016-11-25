package com.jack.autotree.builders;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ErasedType<T>
{
  private class Erasure<K>
  {
    
  }
  
  //private final ErasedType<T> erased = new ErasedType<T>(){ };
  private final ParameterizedType pt;
  
  public ErasedType()
  {
    Erasure<T> erased = new Erasure<T>(){ };
    this.pt = (ParameterizedType)erased.getClass().getGenericSuperclass();
  }
  
  public Type getRawType() { return pt.getRawType(); }
  public Type[] getTypeArguments() { return pt.getActualTypeArguments(); }
  
  public String toString() { 
    return getRawType() + "<" + 
      Arrays.toString(pt.getActualTypeArguments());
        
        //String.join(",", Arrays.stream(getTypeArguments()).map(Object::toString).toArray(i -> new String[i])) + ">";
  }
}
