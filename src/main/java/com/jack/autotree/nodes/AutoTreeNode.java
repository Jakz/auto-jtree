package com.jack.autotree.nodes;

import javax.swing.tree.TreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.Enumeration;
import java.util.Iterator;

public abstract class AutoTreeNode implements MutableTreeNode, Iterable<AutoTreeNode>
{
  protected AutoTreeNode parent;
  
  abstract public int getChildCount();
  abstract public AutoTreeNode getChildAt(int index);
  abstract public boolean isLeaf();
  abstract public int getIndex(AutoTreeNode node);
  abstract public Enumeration<AutoTreeNode> children();
  public boolean getAllowsChildren() { return false; }
  
  public Iterator<AutoTreeNode> iterator()
  { 
    throw new UnsupportedOperationException("Iteration is unsupported on node type '"+this.getClass().getCanonicalName()+"'"); 
  }
  
  abstract String mnemonic();
  public String toString() { return mnemonic(); }
  
  @Override public void setParent(MutableTreeNode node) { this.parent = (AutoTreeNode)node; }
  @Override public AutoTreeNode getParent() { return parent; }
  @Override public int getIndex(TreeNode node) { return getIndex((AutoTreeNode)node); }
  
  public void removeFromParent() { getParent().remove(this); }
  
  public void remove(int index) { }
  public void remove(MutableTreeNode node) { }
  public void insert(MutableTreeNode node, int index) { }
  public void setUserObject(Object object) { System.out.println(object.getClass()); }
  
  public boolean isEditable() { return false; }
  
  public boolean isExtensible() { return false; }
  
  public void clear()
  {
    throw new UnsupportedOperationException("clear() is unsupported on node type '"+this.getClass().getCanonicalName()+"'"); 
  }
  
  public void addElement(int index)
  {
    throw new UnsupportedOperationException("addElement() is unsupported on node type '"+this.getClass().getCanonicalName()+"'"); 

  }
}