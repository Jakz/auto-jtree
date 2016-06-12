package com.jack.autotree.proxies;

import java.util.List;

public class ListProxy extends ValueProxy
{
  private final int index;
  private final boolean isEditable;
  
  public ListProxy(ValueProxy parent, int index, boolean isEditable)
  {
    super(parent);
    this.index = index;
    this.isEditable = isEditable;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T get()
  {
    return ((List<T>)parent.get()).get(index);
  }
  
  @SuppressWarnings("unchecked")
  public <T> void set(T value)
  {
    ((List<T>)parent.get()).set(index, value);
  }
  
  public String mnemonic() { return "["+index+"]"; }
  
  public boolean isEditable() { return isEditable; }
}