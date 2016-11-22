package com.jack.autotree.nodes;

import java.util.Enumeration;

public abstract class LeafNode extends AutoTreeNode
{
  @Override public int getChildCount() { return 0; }
  @Override public AutoTreeNode getChildAt(int index) { return null; }
  @Override public boolean isLeaf() { return true; }
  @Override public int getIndex(AutoTreeNode node) { return -1; }
  @Override public Enumeration<AutoTreeNode> children() { return null; }
}