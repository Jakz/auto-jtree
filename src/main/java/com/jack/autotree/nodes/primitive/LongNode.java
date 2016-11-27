package com.jack.autotree.nodes.primitive;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class LongNode extends PrimitiveNode
{
  public LongNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Long.valueOf((Long)getValue());
  }
}