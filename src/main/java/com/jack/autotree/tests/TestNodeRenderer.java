package com.jack.autotree.tests;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.tree.DefaultTreeCellRenderer;

import com.jack.autotree.renderers.AutoNodeRenderer;

public class TestNodeRenderer extends DefaultTreeCellRenderer implements AutoNodeRenderer
{
  TestNodeRenderer()
  {
    this. setTextNonSelectionColor(Color.RED);
  }
  
  @Override public Dimension getPreferredSize() { return new Dimension(200,50); }
}
