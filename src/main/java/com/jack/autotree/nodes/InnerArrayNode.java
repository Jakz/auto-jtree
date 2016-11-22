package com.jack.autotree.nodes;

import java.lang.reflect.Array;

public class InnerArrayNode extends InnerNode
{
  private final Class<?> clazz;
  
  public InnerArrayNode(Object object, Class<?> clazz)
  {
    super(object);
    this.clazz = clazz;
  }
  
  public InnerArrayNode(String caption, Object object, Class<?> clazz)
  {
    super(caption, object);
    this.clazz = clazz;
  }
  
  @Override
  public boolean isExtensible()
  {
    return true;
  }
  
  public void extend(int index)
  {
    if (object == null)
    {
      object = Array.newInstance(clazz.getComponentType(), 1);
    }
  }
}
