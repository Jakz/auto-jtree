package com.jack.autotree.nodes;

import java.lang.reflect.Array;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.instancers.Instancer;
import com.jack.autotree.proxies.ArrayProxy;
import com.jack.autotree.proxies.ValueProxy;

public class InnerArrayNode<T> extends InnerNode<T[]>
{
  private final Class<?> clazz;
  
  public InnerArrayNode(AutoTreeBuilder builder, ValueProxy proxy, Object object, Class<?> clazz)
  {
    super(builder, proxy, object);
    this.clazz = clazz;
  }
  
  public InnerArrayNode(AutoTreeBuilder builder, ValueProxy proxy, String caption, Object object, Class<?> clazz)
  {
    super(builder, proxy, caption, object);
    this.clazz = clazz;
  }
  
  @Override
  public boolean isExtensible()
  {
    return true;
  }
  
  protected void refreshObject(Object object)
  {
    this.object = object;
    proxy.set(object);
    rebuildIndices();
  }
  
  protected void rebuildIndices()
  {
    for (int i = 0; i < getChildCount(); ++i)
      children.get(i).proxy = new ArrayProxy(proxy, parent, i, true);
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
      
      newArray[index] = Instancer.istantiate(clazz.getComponentType());
      children.add(index, builder.build(newArray[index], clazz));
      
      refreshObject(newArray);
    }
  }
  
  @Override public void removeElement(int index)
  {
    if (!proxy.isEditable())
      throw new UnsupportedOperationException("ArrayNode cannot be modified as its proxy is not editable");
    
    if (object != null)
    {
      Object[] array = (Object[])object;
      Object[] newArray = (Object[])Array.newInstance(clazz.getComponentType(), array.length-1);

      if (index > 0)
        System.arraycopy(array, 0, newArray, 0, index);
      if (index < array.length - 1)
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
      
      children.remove(index);
      refreshObject(newArray);
    }
  }
}
