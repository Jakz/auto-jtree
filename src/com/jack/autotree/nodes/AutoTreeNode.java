package com.jack.autotree.nodes;

import javax.swing.tree.TreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.Enumeration;

public abstract class AutoTreeNode implements MutableTreeNode
{
  protected AutoTreeNode parent;
  
  abstract public int getChildCount();
  abstract public AutoTreeNode getChildAt(int index);
  abstract public boolean isLeaf();
  abstract public int getIndex(AutoTreeNode node);
  @SuppressWarnings("rawtypes")
  abstract public Enumeration children();
  public boolean getAllowsChildren() { return false; }
  
  abstract String mnemonic();
  public String toString() { return mnemonic(); }
  
  public void setParent(MutableTreeNode node) { this.parent = (AutoTreeNode)node; }
  public AutoTreeNode getParent() { return parent; }
  public int getIndex(TreeNode node) { return getIndex((AutoTreeNode)node); }
  
  public void removeFromParent() { }
  public void remove(int index) { }
  public void remove(MutableTreeNode node) { }
  public void insert(MutableTreeNode node, int index) { }
  public void setUserObject(Object object) { System.out.println(object.getClass()); }
  
  public boolean isEditable() { return false; }
}