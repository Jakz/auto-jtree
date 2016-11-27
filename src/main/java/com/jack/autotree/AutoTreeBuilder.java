package com.jack.autotree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.jack.autotree.builders.*;
import com.jack.autotree.builders.array.TreeBuilderIntArray;
import com.jack.autotree.builders.array.TreeBuilderObjectArray;
import com.jack.autotree.builders.primitive.BooleanTreeBuilder;
import com.jack.autotree.builders.primitive.ByteTreeBuilder;
import com.jack.autotree.builders.primitive.CharacterTreeBuilder;
import com.jack.autotree.builders.primitive.DoubleTreeBuilder;
import com.jack.autotree.builders.primitive.FloatTreeBuilder;
import com.jack.autotree.builders.primitive.IntegerTreeBuilder;
import com.jack.autotree.builders.primitive.LongTreeBuilder;
import com.jack.autotree.builders.primitive.ShortTreeBuilder;
import com.jack.autotree.builders.primitive.StringTreeBuilder;
import com.jack.autotree.instancers.Instancer;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.RootProxy;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.types.GenericType;
import com.jack.autotree.types.RawType;

public class AutoTreeBuilder
{
  final private Map<AutoType, TreeBuilder> builders;
  final private Map<AutoType, TreeBuilder> inheritanceBuilders;
  final private Map<AutoType, TreeBuilder> inheritanceCache;
  final private TreeBuilder reflectiveBuilder;
  final private List<Instancer> instancers;
  final private AutoTreeContext context;
  
  private void registerBuilder(Class<?> type, TreeBuilder builder)
  {
    builders.put(new RawType(type), builder);
  }
  
  public void registerHierarchyBuilder(AutoType type, TreeBuilder builder)
  {
    inheritanceBuilders.put(type, builder);
  }
  
  public TreeBuilder getBuilder(AutoType type)
  {
    TreeBuilder builder =  builders.getOrDefault(type, inheritanceCache.getOrDefault(type, null));
    
    if (builder == null)
    {
      Optional<Map.Entry<AutoType, TreeBuilder>> ibuilder = inheritanceBuilders.entrySet().stream().
          filter( e -> type.isSubtypeOf(e.getKey())).findFirst();
      
      if (ibuilder.isPresent())
      {
        inheritanceCache.put(type, ibuilder.get().getValue());
        return ibuilder.get().getValue();
      }
    }
    else if (builder != null)
      return builder;
    
    return reflectiveBuilder;
  }
  
  private void registerStandardBuilders()
  {
    registerBuilder(Float.TYPE, new FloatTreeBuilder());
    registerBuilder(Float.class, new FloatTreeBuilder());
    registerBuilder(Double.TYPE, new DoubleTreeBuilder());
    registerBuilder(Double.class, new DoubleTreeBuilder());
    registerBuilder(Integer.TYPE, new IntegerTreeBuilder());
    registerBuilder(Integer.class, new IntegerTreeBuilder());
    registerBuilder(Byte.TYPE, new ByteTreeBuilder());
    registerBuilder(Byte.class, new ByteTreeBuilder());
    registerBuilder(Short.TYPE, new ShortTreeBuilder());
    registerBuilder(Short.class, new ShortTreeBuilder());
    registerBuilder(Character.TYPE, new CharacterTreeBuilder());
    registerBuilder(Character.class, new CharacterTreeBuilder());
    registerBuilder(Long.TYPE, new LongTreeBuilder());
    registerBuilder(Long.class, new LongTreeBuilder());
    registerBuilder(Boolean.TYPE, new BooleanTreeBuilder());
    registerBuilder(Boolean.class, new BooleanTreeBuilder());
    registerBuilder(String.class, new StringTreeBuilder());
    
    registerHierarchyBuilder(new RawType(Object[].class), new TreeBuilderObjectArray());
    registerBuilder(int[].class, new TreeBuilderIntArray());
    
    registerHierarchyBuilder(new RawType(List.class), new TreeBuilderList());
    
    instancers.add(Instancer.PRIMITIVE_INSTANCER);
    instancers.add(Instancer.ARRAY_INSTANCER);
    instancers.add(Instancer.DEFAULT_INSTANCER);
  }
  
  public AutoTreeBuilder()
  {
    builders = new HashMap<>();
    inheritanceBuilders = new HashMap<>();
    inheritanceCache = new HashMap<>();
    reflectiveBuilder = new TreeBuilderReflective();
    
    instancers = new ArrayList<>();
    
    registerStandardBuilders();
    
    context = new AutoTreeContext(this);
  }
  
  public <T> TreeModel generate(T o, Class<T> clazz)
  {
    return generate(o, new RawType(clazz));
  }
  
  public TreeModel generate(Object o, AutoType type)
  {
    context.push(new RootProxy());
    return new DefaultTreeModel(build(o, type), false);
  }
  
  public AutoTreeNode build(Object o, AutoType type)
  {
    TreeBuilder builder = getBuilder(type);
    return builder.build(o, type, context);
  }
  
  /*
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
  
  private <T> AutoTreeNode<?> build(List<?> o, GenericType type)
  {
    TreeBuilderList builder = new TreeBuilderList(type);
    return builder.build(o, context);
  }
  
  @SuppressWarnings("unchecked")
  public <T> AutoTreeNode build(Object o, Class<T> clazz)
  {
    //Class<?> clazz = o.getClass();
    TreeBuilder nativeBuilder = builders.get(clazz);
  
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
      
      TreeBuilder builder = nativeBuilder;
      
      if (builder == null)
      {
        builder = new TreeBuilderReflective(clazz);
        builders.put(clazz, builder);
      }
              
      return ((TreeBuilder<T>)builder).build((T)o, context);
    }
  }*/
  
  public Object instantiate(Class<?> clazz)
  {
    Object object = null;
    
    for (int i = 0; i < instancers.size() && object == null; ++i)
      object = instancers.get(i).instantiate(clazz);
    
    return object;
  }

}
