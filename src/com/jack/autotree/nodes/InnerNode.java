package com.jack.autotree.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class InnerNode extends AutoTreeNode
{
  private final List<AutoTreeNode> children;
  private final Object object;
  private String caption;
  
  public InnerNode(Object object)
  {
    this.object = object;
    children = new ArrayList<AutoTreeNode>();
  }
  
  public InnerNode(String caption, Object object)
  {
    this(object);
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
  
  @SuppressWarnings("rawtypes")
  @Override
  public Enumeration children()
  {
    return Collections.enumeration(children);
  }
  
  @Override public String mnemonic()
  {
    return caption != null ? caption : object.toString();
  }
  
  public void add(AutoTreeNode child)
  {
    children.add(child);
  }
}