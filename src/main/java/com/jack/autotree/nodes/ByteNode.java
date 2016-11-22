package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class ByteNode extends PrimitiveNode<Byte>
{
  public ByteNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Byte.valueOf(getValue());
  }
}