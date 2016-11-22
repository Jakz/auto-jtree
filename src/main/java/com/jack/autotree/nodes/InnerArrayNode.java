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
  
  @Override public void addElement(int index)
  {
    if (!proxy.isEditable())
      throw new UnsupportedOperationException("ArrayNode cannot be modified as its proxy is not editable");
    
    if (object != null)
    {
      Object[] array = (Object[])object;
      Object[] newArray = (Object[])Array.newInstance(clazz.getComponentType(), array.length+1);
      
      if (index == 0)
        System.arraycopy(array, 0, newArray, 1, array.length);
      else if (index == array.length-1)
        System.arraycopy(array, 0, newArray, 0, array.length);
      else
      {
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index+1, array.length - index);
      }
      
      /* recreate node structure */ 
      
      proxy.set(newArray);
    }
  }
}
