package com.jack.autotree.types;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

public class GenericType implements AutoType
{
  private final Class<?> rawType;
  private final AutoType[] genericTypes;
  private int hashCode = 0;
  
  public GenericType(Class<?> rawType, AutoType... genericTypes)
  {
    this.rawType = rawType;
    this.genericTypes = genericTypes;
  }
  
  public GenericType(Class<?> rawType, Type... genericTypes)
  {
    this.rawType = rawType;
    this.genericTypes = Arrays.stream(genericTypes).map(t -> new RawType((Class<?>)t)).toArray(i -> new AutoType[i]);
  }
  
  public AutoType getTypeArgument(int index) { return genericTypes[index]; }
  @Override public Class<?> get() { return rawType; }
  
  @Override public Object newInstance() throws IllegalAccessException, InstantiationException { return rawType.newInstance(); }
  
  /*@Override public boolean equals(Object other)
  { 
    return other instanceof GenericType && 
        ((GenericType)other).rawType == rawType && 
        Arrays.equals(((GenericType)other).genericTypes, genericTypes);
  }*/
  
  /*@Override public int hashCode()
  {
    if (hashCode == 0)
    {
      Object[] data = new Object[genericTypes.length+1];
      System.arraycopy(genericTypes, 0, data, 0, genericTypes.length);
      data[genericTypes.length] = rawType;
      hashCode = Objects.hash(data);
    }
    
    return hashCode;
  }*/
  
  @Override public boolean equals(Object o) { return o instanceof AutoType && get() == ((AutoType)o).get(); }
  @Override public int hashCode() { return rawType.hashCode(); }
  
  @Override public String toString()
  { 
    return rawType.getCanonicalName() + "<" + 
        String.join(",", Arrays.stream(genericTypes).map(Object::toString).toArray(i -> new String[i])) + ">";
  }
}
