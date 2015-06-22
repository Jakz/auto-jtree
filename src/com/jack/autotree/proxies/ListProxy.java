package com.jack.autotree.proxies;

import java.util.List;

public class ListProxy implements ValueProxy
{
  private final List<?> parent;
  private final int index;
  private final boolean isEditable;
  
  public ListProxy(List<?> parent, int index, boolean isEditable)
  {
    this.parent = parent;
    this.index = index;
    this.isEditable = isEditable;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T get()
  {
    return ((List<T>)parent).get(index);
  }
  
  @SuppressWarnings("unchecked")
  public <T> void set(T value)
  {
    ((List<T>)parent).set(index, value);
  }
  
  public String mnemonic() { return "["+index+"]"; }
  
  public boolean isEditable() { return isEditable; }
}