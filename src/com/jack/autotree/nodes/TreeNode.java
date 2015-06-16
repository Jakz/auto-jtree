package com.jack.autotree.nodes;

public abstract class TreeNode
{
  abstract public int size();
  abstract public TreeNode get(int index);
  
  abstract String mnemonic();
  public String toString() { return mnemonic(); }
}