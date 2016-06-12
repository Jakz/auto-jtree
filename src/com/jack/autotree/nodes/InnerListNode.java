package com.jack.autotree.nodes;

public class InnerListNode extends InnerNode
{
  private final Class<?> clazz;
  
  public InnerListNode(Object object, Class<?> clazz)
  {
    super(object);
    this.clazz = clazz;
  }
  
  public InnerListNode(String caption, Object object, Class<?> clazz)
  {
    super(caption, object);
    this.clazz = clazz;
  }
}
