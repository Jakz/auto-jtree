package com.jack.autotree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jack.autotree.builders.*;
import com.jack.autotree.nodes.TreeNode;

public class AutoTree
{
  final private Map<Class<?>, TreeBuilder<?>> builders;
  final private AutoTreeContext context;
  
  public AutoTree()
  {
    builders = new HashMap<Class<?>, TreeBuilder<?>>();
    context = new AutoTreeContext(this);
    
    builders.put(Float.class, new FloatTreeBuilder());
    builders.put(Double.class, new DoubleTreeBuilder());
    builders.put(Integer.class, new IntegerTreeBuilder());
    builders.put(Byte.class, new ByteTreeBuilder());
    builders.put(Short.class, new ShortTreeBuilder());
    builders.put(Character.class, new CharacterTreeBuilder());
    builders.put(Long.class, new LongTreeBuilder());
    builders.put(Boolean.class, new BooleanTreeBuilder());
  }
  
  public <T> AutoTreeModel generate(T o)
  {
    return new AutoTreeModel(build(o));
  }
  
  @SuppressWarnings("unchecked") <T> TreeNode build(T o)
  {
    Class<?> clazz = o.getClass();
    	    
    if (clazz.isArray())
    {
      TreeBuilderArray<T> builder = new TreeBuilderArray<T>();
      return ((TreeBuilderArray<T>)builder).build((T[])o, context);
    }
    else if (List.class.isAssignableFrom(clazz))
    {
      TreeBuilderList<T> builder = new TreeBuilderList<T>();
      return ((TreeBuilderList<T>)builder).build((List<T>)o, context);
    }
    else
    {
      TreeBuilder<?> builder = builders.get(clazz);
      
      if (builder == null)
      {
   
        builder = new TreeBuilderReflective<T>();
        builders.put(clazz, builder);
      }
              
      return ((TreeBuilder<T>)builder).build(o, context);
    }
  }
}