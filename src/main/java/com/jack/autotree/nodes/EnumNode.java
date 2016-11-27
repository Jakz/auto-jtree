package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class EnumNode extends LeafNode
{
  public EnumNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy); 
  }

  @Override
  public String mnemonic()
  {
    return proxy.mnemonic() + ": " + proxy.get().toString();
  }

}
