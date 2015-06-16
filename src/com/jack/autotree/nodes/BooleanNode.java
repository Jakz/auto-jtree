package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class BooleanNode extends PrimitiveNode<Boolean>
{
  public BooleanNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Boolean.valueOf(getValue());
  }
}