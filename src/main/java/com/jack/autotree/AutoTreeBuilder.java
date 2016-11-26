package com.jack.autotree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.jack.autotree.builders.*;
import com.jack.autotree.instancers.Instancer;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.RootProxy;

public class AutoTreeBuilder
{
  final private Map<Class<?>, TreeBuilder<?>> builders;
  final private List<Instancer> instancers;
  final private AutoTreeContext context;
  
  private void registerStandardBuilders()
  {
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
    
    instancers.add(Instancer.PRIMITIVE_INSTANCER);
    instancers.add(Instancer.ARRAY_INSTANCER);
    instancers.add(Instancer.DEFAULT_INSTANCER);
  }
  
  public AutoTreeBuilder()
  {
    builders = new HashMap<>();
    instancers = new ArrayList<>();
    
    registerStandardBuilders();
    
    context = new AutoTreeContext(this);
  }
  
  public <T> void registerBuilder(Class<T> clazz, TreeBuilder<T> builder)
  {
    builders.put(clazz, builder);
  }
  
  public <T> TreeModel generate(T o, Class<T> clazz)
  {
    context.push(new RootProxy());
    return new DefaultTreeModel(build(o, clazz), false);
  }
  
  private <T> AutoTreeNode build(T[] o, Class<T> clazz)
  {
    Logger.logger.log("Generating array tree for "+clazz.getCanonicalName());
    TreeBuilderArray<T[], T> builder = new TreeBuilderArray<>(clazz);
    return builder.build(o, context);
  }
  
  private AutoTreeNode build(int[] o)
  {
    Logger.logger.log("Generating int array tree");
    TreeBuilderArray<Integer[], Integer> builder = new TreeBuilderArray<>(Integer.TYPE);
    return builder.build(o, context);
  }
  
  private <T> AutoTreeNode build(List<T> o, Class<T> clazz)
  {
    TreeBuilderList<T> builder = new TreeBuilderList<>(clazz);
    return builder.build(o, context);
  }
  
  @SuppressWarnings("unchecked")
  public <T> AutoTreeNode build(Object o, Class<T> clazz)
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
      Class<?> componentType = o.getClass().getComponentType();
      
      if (componentType.isPrimitive())
      {
        if (componentType == Integer.TYPE)
          return build((int[])o);
        else
          return null;
      }
      else
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
  
  public Object instantiate(Class<?> clazz)
  {
    Object object = null;
    
    for (int i = 0; i < instancers.size() && object == null; ++i)
      object = instancers.get(i).instantiate(clazz);
    
    return object;
  }

}
