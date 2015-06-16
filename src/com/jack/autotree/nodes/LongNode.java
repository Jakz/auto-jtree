package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class LongNode extends PrimitiveNode<Short>
{
  public LongNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Short.valueOf(getValue());
  }
}