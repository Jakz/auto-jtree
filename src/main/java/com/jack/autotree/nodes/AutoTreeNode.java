package com.jack.autotree.nodes;

import javax.swing.tree.TreeNode;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

import javax.swing.tree.MutableTreeNode;
import java.util.Enumeration;
import java.util.Iterator;

public abstract class AutoTreeNode implements MutableTreeNode, Iterable<AutoTreeNode>
{
  protected final AutoTreeBuilder builder;
  protected InnerNode parent;
  protected ValueProxy proxy;
  
  abstract public int getChildCount();
  abstract public AutoTreeNode getChildAt(int index);
  abstract public boolean isLeaf();
  abstract public int getIndex(AutoTreeNode node);
  abstract public Enumeration<AutoTreeNode> children();
  public boolean getAllowsChildren() { return false; }
  
  protected AutoTreeNode(AutoTreeBuilder builder, ValueProxy proxy)
  {
    this.builder = builder;
    this.proxy = proxy;
  }
  
  protected void replaceMe(AutoTreeNode newTree)
  {
    int index = parent.getIndex(this);
    parent.remove(index);
    parent.insert(newTree, index);
  }
  
  public Iterator<AutoTreeNode> iterator()
  { 
    throw new UnsupportedOperationException("Iteration is unsupported on node type '"+this.getClass().getCanonicalName()+"'"); 
  }
  
  public abstract String mnemonic();
  public String toString() { return mnemonic(); }
  
  @Override public void setParent(MutableTreeNode node) { this.parent = (InnerNode)node; }
  @Override public AutoTreeNode getParent() { return parent; }
  @Override public int getIndex(TreeNode node) { return getIndex((AutoTreeNode)node); }
  
  public void removeFromParent() { getParent().remove(this); }
  
  public void remove(int index) { }
  public void remove(MutableTreeNode node) { }
  public void insert(MutableTreeNode node, int index) { }
    
  public boolean isExtensible() { return false; }
  
  public void clear()
  {
    throw new UnsupportedOperationException("clear() is unsupported on node type '"+this.getClass().getCanonicalName()+"'"); 
  }
  
  public void addElement(int index)
  {
    throw new UnsupportedOperationException("addElement() is unsupported on node type '"+this.getClass().getCanonicalName()+"'"); 
  }
  
  public void removeElement(int index)
  {
    throw new UnsupportedOperationException("removeElement() is unsupported on node type '"+this.getClass().getCanonicalName()+"'"); 
  }
  
  public Object getValue() { return proxy.get(); }
  void setValue(Object value) { proxy.set(value); }
  
  @Override
  @SuppressWarnings("unchecked")
  public void setUserObject(Object object) { setValue(object); }
  
  public boolean isEditable() { return proxy.isEditable(); }
}