package com.jack.autotree.renderers;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import com.jack.autotree.nodes.AutoTreeNode;

public class TreeRendererDispatcher implements TreeCellRenderer
{
  private final Map<Class<?>, AutoNodeRenderer> renderers;
  private final Map<Class<?>, AutoNodeRenderer> inheritanceCache;
  private final Map<Class<?>, AutoNodeRenderer> inheritanceRenderers;
  private final DefaultTreeCellRenderer baseRenderer;
  
  public TreeRendererDispatcher()
  {
    renderers = new HashMap<>();
    inheritanceRenderers = new HashMap<>();
    inheritanceCache = new HashMap<>();
    baseRenderer = new DefaultTreeCellRenderer();
  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    AutoTreeNode<?> node = (AutoTreeNode<?>)value;
    Class<?> clazz = node.getValue() != null ? node.getValue().getClass() : null;
    System.out.println(clazz);
    AutoNodeRenderer renderer = renderers.get(clazz);
    if (renderer != null)
      return renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    
    renderer = inheritanceCache.get(clazz);
    if (renderer != null)
      return renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    
     for (Map.Entry<Class<?>, AutoNodeRenderer> entry : inheritanceRenderers.entrySet())
     {
       if (entry.getKey().isAssignableFrom(clazz))
       {
         inheritanceCache.put(clazz, entry.getValue());
         return entry.getValue().getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
       }
     }

     return baseRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
  }
  
  public void registerRenderer(Class<?> clazz, AutoNodeRenderer renderer)
  {
    renderers.put(clazz, renderer);
  }
  
  public void registerHierarchyRenderer(Class<?> clazz, AutoNodeRenderer renderer)
  {
    inheritanceRenderers.put(clazz, renderer);
  }
}
