package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public abstract class PrimitiveNode<T> extends LeafNode<T>
{  
  PrimitiveNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    super(builder, proxy);
  }
}