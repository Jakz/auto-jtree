package com.jack.autotree.nodes;

import java.util.List;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.builders.TreeBuilderList;
import com.jack.autotree.proxies.ListProxy;
import com.jack.autotree.proxies.ValueProxy;
import com.jack.autotree.types.GenericType;

public class InnerListNode extends InnerNode
{
  private final GenericType type;
  
  public InnerListNode(AutoTreeBuilder builder, ValueProxy proxy, Object object, GenericType type)
  {
    super(builder, proxy, object);
    this.type = type;
  }
  
  public InnerListNode(AutoTreeBuilder builder, ValueProxy proxy, String caption, Object object, GenericType type)
  {
    super(builder, proxy, caption, object);
    this.type = type;
  }
  
  protected void rebuildIndices()
  {
    for (int i = 0; i < getChildCount(); ++i)
      children.get(i).proxy = new ListProxy(proxy, parent, i, true);
  }
  
  @Override
  public boolean isExtensible()
  {
    return true;
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
      Object newObject = builder.instantiate(type.getTypeArgument(0).get());
      data.add(index, newObject);
      children.add(index, builder.build(newObject, type.getTypeArgument(0)));
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
