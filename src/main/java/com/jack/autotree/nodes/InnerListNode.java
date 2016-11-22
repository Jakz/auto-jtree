package com.jack.autotree.nodes;

import java.util.List;

import com.jack.autotree.builders.TreeBuilderList;
import com.jack.autotree.proxies.ValueProxy;

public class InnerListNode extends InnerNode
{
  private final Class<?> clazz;
  
  public InnerListNode(ValueProxy proxy, Object object, Class<?> clazz)
  {
    super(proxy, object);
    this.clazz = clazz;
  }
  
  public InnerListNode(ValueProxy proxy, String caption, Object object, Class<?> clazz)
  {
    super(proxy, caption, object);
    this.clazz = clazz;
  }
  
  @Override public void clear()
  {
    ((List<?>)object).clear();
    children.clear();
  }
  
  @Override public void addElement(int index)
  {
    try
    {
      List data = (List)object;
      data.add(index, clazz.newInstance());
      // TODO:finish
    }
    catch (Exception e)
    {
      // check if it's instantiable and throw exception
      e.printStackTrace();
    }
  }
}
