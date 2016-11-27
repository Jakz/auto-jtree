package com.jack.autotree.nodes.primitive;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class ShortNode extends PrimitiveNode
{
  public ShortNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Short.valueOf((Short)getValue());
  }
}