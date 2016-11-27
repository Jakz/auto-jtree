package com.jack.autotree.renderers;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.types.RawType;
import com.jack.autotree.types.TypeMapper;

public class TreeRendererDispatcher implements TreeCellRenderer
{
  private final TypeMapper<RawType, AutoNodeRenderer> renderers;
    
  public TreeRendererDispatcher()
  {
    renderers = new TypeMapper<>();
    renderers.setDefault(new WrapperNodeRenderer(new DefaultTreeCellRenderer()));
  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    AutoTreeNode node = (AutoTreeNode)value;
    Class<?> clazz = node.getValue() != null ? node.getValue().getClass() : null;
    AutoNodeRenderer renderer = renderers.get(clazz != null ? new RawType(clazz) : null);

     return renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
  }
  
  public void registerRenderer(Class<?> clazz, AutoNodeRenderer renderer)
  {
    renderers.register(new RawType(clazz), renderer);
  }
  
  public void registerHierarchyRenderer(Class<?> clazz, AutoNodeRenderer renderer)
  {
    renderers.registerHierarchy(new RawType(clazz), renderer);
  }
}
