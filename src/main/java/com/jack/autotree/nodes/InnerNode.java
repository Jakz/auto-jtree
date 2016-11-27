package com.jack.autotree.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.swing.tree.MutableTreeNode;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public abstract class InnerNode extends AutoTreeNode
{
  protected final List<AutoTreeNode> children;
  protected Object object;
  protected String caption;
  
  public InnerNode(AutoTreeBuilder builder, ValueProxy proxy, Object object)
  {
    super(builder, proxy);
    this.object = object;
    children = new ArrayList<>();
    
  }
  
  public InnerNode(AutoTreeBuilder builder, ValueProxy proxy, String caption, Object object)
  {
    this(builder, proxy, object);
    this.caption = caption;
  }
  
  @Override public int getChildCount() 
  { 
    return children.size();
  }
  
  @Override public AutoTreeNode getChildAt(int index)
  {
    return children.get(index);
  }
  
  @Override public boolean isLeaf()
  {
    return false;
  }
  
  @Override public int getIndex(AutoTreeNode node)
  {
    for (int i = 0; i < children.size(); ++i)
      if (children.get(i) == node)
        return i;
    
    return -1;
  }
  
  @Override public Iterator<AutoTreeNode> iterator()
  {
    return children.iterator();
  }
  
  @Override
  public Enumeration<AutoTreeNode> children()
  {
    return Collections.enumeration(children);
  }
  
  @Override public String mnemonic()
  {
    return caption != null ? caption : object.getClass().getSimpleName();
  }
  
  public void add(AutoTreeNode child)
  {
    children.add(child);
  }
  
  @Override
  public void remove(int index)
  {
    children.remove(index);
  }
  
  @Override
  public void remove(MutableTreeNode node)
  {
    children.remove((AutoTreeNode)node);
  }
  
  @Override
  public void insert(MutableTreeNode node, int index)
  {
    children.add(index, (AutoTreeNode)node);
  }
  
  public Object getObject() { return object; }
}