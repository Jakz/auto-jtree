package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class FloatNode extends PrimitiveNode
{
  public FloatNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Float.valueOf((Float)getValue());
  }
}