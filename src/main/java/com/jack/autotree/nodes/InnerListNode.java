package com.jack.autotree.nodes;

import java.util.List;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.builders.TreeBuilderList;
import com.jack.autotree.proxies.ArrayProxy;
import com.jack.autotree.proxies.ListProxy;
import com.jack.autotree.proxies.ValueProxy;

public class InnerListNode<T> extends InnerNode<List<T>>
{
  private final Class<?> clazz;
  
  public InnerListNode(AutoTreeBuilder builder, ValueProxy proxy, Object object, Class<?> clazz)
  {
    super(builder, proxy, object);
    this.clazz = clazz;
  }
  
  public InnerListNode(AutoTreeBuilder builder, ValueProxy proxy, String caption, Object object, Class<?> clazz)
  {
    super(builder, proxy, caption, object);
    this.clazz = clazz;
  }
  
  protected void rebuildIndices()
  {
    for (int i = 0; i < getChildCount(); ++i)
      children.get(i).proxy = new ListProxy(proxy, parent, i, true);
  }
  
  @Override public void clear()
  {
    ((List<?>)object).clear();
    children.clear();
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override public void addElement(int index)
  {
    try
    {
      List data = (List)object;
      Object newObject = builder.instantiate(clazz);
      data.add(index, newObject);
      children.add(index, builder.build(newObject, clazz));
      rebuildIndices();
      // TODO:finish
    }
    catch (Exception e)
    {
      // check if it's instantiable and throw exception
      e.printStackTrace();
    }
  }
}
