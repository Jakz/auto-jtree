package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class ByteNode extends PrimitiveNode
{
  public ByteNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Byte.valueOf((Byte)getValue());
  }
}