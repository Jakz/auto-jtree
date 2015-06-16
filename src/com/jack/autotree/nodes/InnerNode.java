package com.jack.autotree.nodes;

import java.util.ArrayList;
import java.util.List;

public class InnerNode extends TreeNode
{
  final List<TreeNode> children;
  final Object object;
  String caption;
  
  public InnerNode(Object object)
  {
    this.object = object;
    children = new ArrayList<TreeNode>();
  }
  
  public InnerNode(String caption, Object object)
  {
    this(object);
    this.caption = caption;
  }
  
  @Override public int size() 
  { 
    return children.size();
  }
  
  @Override public TreeNode get(int index)
  {
    return children.get(index);
  }
  
  @Override public String mnemonic()
  {
    return caption != null ? caption : object.toString();
  }
  
  public void add(TreeNode child)
  {
    children.add(child);
  }
}