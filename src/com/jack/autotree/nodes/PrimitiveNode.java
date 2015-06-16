package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public abstract class PrimitiveNode<T> extends LeafNode
{
  final ValueProxy proxy;
  
  PrimitiveNode(ValueProxy proxy)
  {
    this.proxy = proxy;
  }
  
  T getValue() { return proxy.get(); }
  void setValue(T value) { proxy.set(value); }
}