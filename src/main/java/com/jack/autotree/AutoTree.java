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
  final private Map<Class<?>, TreeBuilder<?>> builders;
  final private AutoTreeContext context;
  
  public AutoTree()
  {
    super();
    
    builders = new HashMap<Class<?>, TreeBuilder<?>>();
    context = new AutoTreeContext(this);
    
    builders.put(Float.TYPE, new FloatTreeBuilder());
    builders.put(Float.class, new FloatTreeBuilder());
    builders.put(Double.TYPE, new DoubleTreeBuilder());
    builders.put(Double.class, new DoubleTreeBuilder());
    builders.put(Integer.TYPE, new IntegerTreeBuilder());
    builders.put(Integer.class, new IntegerTreeBuilder());
    builders.put(Byte.TYPE, new ByteTreeBuilder());
    builders.put(Byte.class, new ByteTreeBuilder());
    builders.put(Short.TYPE, new ShortTreeBuilder());
    builders.put(Short.class, new ShortTreeBuilder());
    builders.put(Character.TYPE, new CharacterTreeBuilder());
    builders.put(Character.class, new CharacterTreeBuilder());
    builders.put(Long.TYPE, new LongTreeBuilder());
    builders.put(Long.class, new LongTreeBuilder());
    builders.put(Boolean.TYPE, new BooleanTreeBuilder());
    builders.put(Boolean.class, new BooleanTreeBuilder());
    builders.put(String.class, new StringTreeBuilder());

    
    setCellEditor(new AutoTreeCellEditor(this));
  }
    
  @Override
  public boolean isPathEditable(TreePath path)
  {
    AutoTreeNode node = (AutoTreeNode)path.getLastPathComponent();
    
    return isEditable() && node.isEditable();
  }
  
  public <T> void generate(T o, Class<T> clazz)
  {
    context.push(new RootProxy());
    setModel(new DefaultTreeModel(build(o, clazz), false));
  }
  
  private <T> AutoTreeNode build(T[] o, Class<T> clazz)
  {
    Logger.logger.log("Generating array tree for "+clazz.getCanonicalName());
    TreeBuilderArray<T[], T> builder = new TreeBuilderArray<>(clazz);
    return builder.build(o, context);
  }
  
  private <T> AutoTreeNode build(List<T> o, Class<T> clazz)
  {
    TreeBuilderList<T> builder = new TreeBuilderList<>(clazz);
    return builder.build(o, context);
  }
  
  @SuppressWarnings("unchecked")
  protected <T> AutoTreeNode build(Object o, Class<T> clazz)
  {
    //Class<?> clazz = o.getClass();
    TreeBuilder<?> nativeBuilder = builders.get(clazz);
  
    if (nativeBuilder == null && o == null)
    {
      Logger.logger.log("Generating null node for "+clazz.getCanonicalName());
      return new NullTreeBuilder<T>(clazz).build(null, context);
    }
    else if (o != null && o.getClass().isArray())
    {
      return build((T[])o, clazz);
    }
    else if (o != null && List.class.isAssignableFrom(o.getClass()))
    {
      return build((List<T>)o, clazz);
    }
    else
    {
      Logger.logger.log("Generating reflective tree for "+clazz.getCanonicalName());
      
      TreeBuilder<?> builder = nativeBuilder;
      
      if (builder == null)
      {
        builder = new TreeBuilderReflective<T>(clazz);
        builders.put(clazz, builder);
      }
              
      return ((TreeBuilder<T>)builder).build((T)o, context);
    }
  }
  
  public boolean extendElement(TreePath path)
  {
    AutoTreeNode node = (AutoTreeNode)path.getLastPathComponent();
    
    System.out.println(path+" extensible: "+node.isExtensible());
    
    return true;
  }
}