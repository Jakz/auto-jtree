package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class IntegerNode extends PrimitiveNode<Integer>
{
  public IntegerNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic() + ": " + Integer.valueOf(getValue());
  }
}