package com.jack.autotree.proxies;

public class RootProxy extends ValueProxy
{
  public RootProxy()
  {
    super(null);
  }
  
  @Override
  public <T> T get()
  {
    return null;
  }

  @Override
  public <T> void set(T value)
  {
    throw new UnsupportedOperationException("Unable to modify value of a root node.");
  }

  @Override
  public String mnemonic()
  {
    return "";
  }

  @Override
  public boolean isEditable()
  {
    return false;
  }
}
