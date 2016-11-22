package com.jack.autotree.nodes;

import java.lang.reflect.Array;

import com.jack.autotree.proxies.ValueProxy;

public class InnerArrayNode extends InnerNode
{
  private final Class<?> clazz;
  
  public InnerArrayNode(ValueProxy proxy, Object object, Class<?> clazz)
  {
    super(proxy, object);
    this.clazz = clazz;
  }
  
  public InnerArrayNode(ValueProxy proxy, String caption, Object object, Class<?> clazz)
  {
    super(proxy, caption, object);
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
