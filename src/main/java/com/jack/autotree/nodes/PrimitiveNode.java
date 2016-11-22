package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public abstract class PrimitiveNode<T> extends LeafNode<T>
{  
  PrimitiveNode(ValueProxy proxy)
  {
    super(proxy);
  }
}