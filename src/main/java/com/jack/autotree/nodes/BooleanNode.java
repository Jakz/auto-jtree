package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class BooleanNode extends PrimitiveNode
{
  public BooleanNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Boolean.valueOf((Boolean)getValue());
  }
}