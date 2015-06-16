package com.jack.autotree.proxies;

import java.util.List;

public class ListProxy implements ValueProxy
{
  private final List<?> parent;
  private final int index;
  
  public ListProxy(List<?> parent, int index)
  {
    this.parent = parent;
    this.index = index;
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
}