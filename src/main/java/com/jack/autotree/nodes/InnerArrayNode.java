package com.jack.autotree.nodes;

import java.lang.reflect.Array;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.instancers.ArrayReflection;
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
    return true; //TODO: check annotation
  }
  
  @Override public String mnemonic()
  {
    return caption + ": " + clazz.getComponentType().getSimpleName()+"["+getChildCount()+"]";
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
      int length = Array.getLength(object);
      
      Object array = object;
      Object newArray = Array.newInstance(clazz.getComponentType(), length+1);
      
      if (index == 0)
        System.arraycopy(array, 0, newArray, 1, length);
      else if (index == length-1)
        System.arraycopy(array, 0, newArray, 0, length);
      else
      {
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index+1, length - index);
      }
      
      Object newValue = builder.instantiate(clazz.getComponentType());
      Array.set(newArray, index, newValue);
      children.add(index, builder.build(newValue, clazz));
      
      refreshObject(newArray);
    }
  }
  
  @Override public void removeElement(int index)
  {
    if (!proxy.isEditable())
      throw new UnsupportedOperationException("ArrayNode cannot be modified as its proxy is not editable");
    
    if (object != null)
    {
      int length = Array.getLength(object);
      
      Object array = object;
      Object newArray = Array.newInstance(clazz.getComponentType(), length-1);

      if (index > 0)
        System.arraycopy(array, 0, newArray, 0, index);
      if (index < length - 1)
        System.arraycopy(array, index + 1, newArray, index, length - index - 1);
      
      children.remove(index);
      refreshObject(newArray);
    }
  }
}
