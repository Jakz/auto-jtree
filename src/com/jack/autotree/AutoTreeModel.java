package com.jack.autotree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.jack.autotree.nodes.LeafNode;
import com.jack.autotree.nodes.TreeNode;

public class AutoTreeModel implements TreeModel
{
  private final TreeNode root;
  
  AutoTreeModel(TreeNode root)
  {
    this.root = root;
  }
  
  public void addTreeModelListener(TreeModelListener listener) { }
  public void removeTreeModelListener(TreeModelListener listener) { }
  
  @Override
  public int getChildCount(Object parent)
  {
    return ((TreeNode)parent).size();
  }
  
  @Override public Object getChild(Object parent, int index)
  {
    return ((TreeNode)parent).get(index);
  }
  
  @Override public int getIndexOfChild(Object parent, Object child)
  {
    TreeNode tparent = (TreeNode)parent;
    
    for (int i = 0; i < tparent.size(); ++i)
      if (tparent.get(i) == child)
        return i;
    
    return 0;
  }
  
  @Override public boolean isLeaf(Object object) {
    return object instanceof LeafNode;
  }
  
  @Override public Object getRoot() { return root; }
  
  @Override public void valueForPathChanged(TreePath path, Object newValue) { }
}