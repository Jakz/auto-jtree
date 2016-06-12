package com.jack.autotree.proxies;

public abstract class ValueProxy
{
  public final ValueProxy parent;
  
  ValueProxy(ValueProxy parent)
  {
    this.parent = parent;
  }
  
  abstract public <T> T get();
  abstract public <T> void set(T value);
  abstract public String mnemonic();
  abstract public boolean isEditable();
}