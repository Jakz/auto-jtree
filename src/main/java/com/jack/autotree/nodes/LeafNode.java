package com.jack.autotree.nodes;

import java.util.Enumeration;

import com.jack.autotree.proxies.ValueProxy;

public abstract class LeafNode<T> extends AutoTreeNode<T>
{
  LeafNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  @Override public int getChildCount() { return 0; }
  @Override public AutoTreeNode getChildAt(int index) { return null; }
  @Override public boolean isLeaf() { return true; }
  @Override public int getIndex(AutoTreeNode node) { return -1; }
  @Override public Enumeration<AutoTreeNode<?>> children() { return null; }
}