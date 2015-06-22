package com.jack.autotree.proxies;

public class ArrayProxy implements ValueProxy
{
  private final Object parent;
  private final int index;
  private final boolean isEditable;
  
  public ArrayProxy(Object parent, int index, boolean isEditable)
  {
    this.parent = parent;
    this.index = index;
    this.isEditable = isEditable;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T get()
  {
    return ((T[])parent)[index];
  }
  
  @SuppressWarnings("unchecked")
  public <T> void set(T value)
  {
    ((T[])parent)[index] = value;
  }
  
  public String mnemonic() { return "["+index+"]"; }
  
  public boolean isEditable() { return isEditable; }

}