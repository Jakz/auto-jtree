package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class StringNode extends PrimitiveNode
{
  public StringNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+(String)getValue();
  }
}