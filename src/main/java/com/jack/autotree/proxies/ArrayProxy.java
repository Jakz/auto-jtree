package com.jack.autotree.proxies;

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
  
  @SuppressWarnings("unchecked")
  public <T> T get()
  {
    return ((T[])parent.get())[index];
  }
  
  @SuppressWarnings("unchecked")
  public <T> void set(T value)
  {
    //((T[])parent.get())[index] = value;
    ((T[])parentNode.getObject())[index] = value;
  }
  
  public String mnemonic() { return "["+index+"]"; }
  
  public boolean isEditable() { return isEditable; }

}