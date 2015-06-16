package com.jack.autotree.nodes;

public abstract class LeafNode extends TreeNode
{
  @Override public int size() { return 0; }
  @Override public TreeNode get(int index) { return null; }
}