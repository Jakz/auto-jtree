package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public abstract class PrimitiveNode extends LeafNode
{  
  PrimitiveNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
}