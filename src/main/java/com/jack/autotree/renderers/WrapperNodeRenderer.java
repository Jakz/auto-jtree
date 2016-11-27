package com.jack.autotree.renderers;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class WrapperNodeRenderer implements AutoNodeRenderer
{
  final private TreeCellRenderer inner;
  
  WrapperNodeRenderer(TreeCellRenderer inner)
  {
    this.inner = inner;
  }
  
  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row,
      boolean hasFocus)
  {
    return inner.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
  }

}
