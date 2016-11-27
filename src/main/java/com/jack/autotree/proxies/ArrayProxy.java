package com.jack.autotree.proxies;

import java.lang.reflect.Array;

import com.jack.autotree.nodes.InnerNode;

public class ArrayProxy extends ValueProxy
{
  private final int index;
  private final boolean isEditable;
  private final InnerNode parentNode;
  
  public ArrayProxy(ValueProxy parent, InnerNode parentNode, int index, boolean isEditable)
  {
    super(parent);
    this.index = index;
    this.isEditable = isEditable;
    this.parentNode = parentNode;
  }
  
  public InnerNode getParentNode()
  {
    return parentNode;
  }
  
  public Object get()
  {
    return Array.get(parent.get(), index);
  }
  
  public void set(Object value)
  {
    Object parent = parentNode.getObject();    
    Array.set(parent, index, value);
  }
  
  public String mnemonic() { return "["+index+"]"; }
  
  public boolean isEditable() { return isEditable; }

}