package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class DoubleNode extends PrimitiveNode
{
  public DoubleNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Double.valueOf((Double)getValue());
  }
}