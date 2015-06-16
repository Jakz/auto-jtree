package com.jack.autotree.proxies;

public class ArrayProxy implements ValueProxy
{
  private final Object parent;
  private final int index;
  
  public ArrayProxy(Object parent, int index)
  {
    this.parent = parent;
    this.index = index;
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
}