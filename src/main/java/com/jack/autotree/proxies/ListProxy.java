package com.jack.autotree.proxies;

import java.util.List;

import com.jack.autotree.nodes.InnerNode;

public class ListProxy extends ValueProxy
{
  private final int index;
  private final boolean isEditable;
  private InnerNode parentNode;
  
  public ListProxy(ValueProxy parent, InnerNode parentNode, int index, boolean isEditable)
  {
    super(parent);
    this.index = index;
    this.isEditable = isEditable;
    this.parentNode = parentNode;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T get()
  {
    return ((List<T>)parent.get()).get(index);
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public <T> void set(T value)
  {
    List object = (List)parentNode.getObject();  
    object.set(index, value);
  }
  
  public String mnemonic() { return "["+index+"]"; }
  
  public boolean isEditable() { return isEditable; }
}