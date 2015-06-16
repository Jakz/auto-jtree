package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class ShortNode extends PrimitiveNode<Long>
{
  public ShortNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Long.valueOf(getValue());
  }
}