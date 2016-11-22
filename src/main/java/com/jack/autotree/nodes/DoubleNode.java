package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class DoubleNode extends PrimitiveNode<Double>
{
  public DoubleNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Double.valueOf(getValue());
  }
}