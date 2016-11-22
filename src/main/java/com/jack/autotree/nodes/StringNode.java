package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class StringNode extends PrimitiveNode<String>
{
  public StringNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+getValue();
  }
}