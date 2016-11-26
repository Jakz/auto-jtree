package com.jack.autotree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JTree;

import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.renderers.AutoNodeRenderer;
import com.jack.autotree.renderers.TreeRendererDispatcher;

public class AutoTree extends JTree
{
  final private AutoTreeBuilder builder;
  final private TreeRendererDispatcher renderer;
  
  public AutoTree()
  {
    super();
    
    builder = new AutoTreeBuilder();
    renderer = new TreeRendererDispatcher();
    
    setModel(null);
    setCellRenderer(renderer);
    setCellEditor(new AutoTreeCellEditor(this));
  }
    
  @Override
  public boolean isPathEditable(TreePath path)
  {
    AutoTreeNode<?> node = (AutoTreeNode<?>)path.getLastPathComponent();
    
    return isEditable() && node.isEditable();
  }
  
  public <T> void generate(T o, Class<T> clazz)
  {;
    setModel(builder.generate(o, clazz));
  }
  
  public boolean extendElement(TreePath path)
  {
    AutoTreeNode<?> node = (AutoTreeNode<?>)path.getLastPathComponent();
    
    if (node.isExtensible())
      node.addElement(0);

    boolean wasExpanded = isExpanded(path);
    boolean wasSelected = isPathSelected(path);
    
    ((DefaultTreeModel)getModel()).nodeStructureChanged(node);
    
    if (wasExpanded)
      expandPath(path);
    
    if (wasSelected)
      setSelectionPath(path);

    return true;
  }
  
  public void registerRenderer(Class<?> clazz, AutoNodeRenderer renderer)
  {
    this.renderer.registerRenderer(clazz, renderer);
  }
  
  public void registerHierarchyRenderer(Class<?> clazz, AutoNodeRenderer renderer)
  {
    this.renderer.registerHierarchyRenderer(clazz, renderer);
  }
}