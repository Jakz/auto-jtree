package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class FloatNode extends PrimitiveNode<Float>
{
  public FloatNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Float.valueOf(getValue());
  }
}