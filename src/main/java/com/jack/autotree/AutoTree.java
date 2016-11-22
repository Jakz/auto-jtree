package com.jack.autotree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JTree;


import com.jack.autotree.builders.*;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.RootProxy;

public class AutoTree extends JTree
{
  final private AutoTreeBuilder builder;
  
  public AutoTree()
  {
    super();
    
    builder = new AutoTreeBuilder();
    setCellEditor(new AutoTreeCellEditor(this));
  }
    
  @Override
  public boolean isPathEditable(TreePath path)
  {
    AutoTreeNode node = (AutoTreeNode)path.getLastPathComponent();
    
    return isEditable() && node.isEditable();
  }
  
  public <T> void generate(T o, Class<T> clazz)
  {;
    setModel(builder.generate(o, clazz));
  }
  
  public boolean extendElement(TreePath path)
  {
    AutoTreeNode node = (AutoTreeNode)path.getLastPathComponent();
    
    System.out.println(path+" extensible: "+node.isExtensible());
    
    return true;
  }
}