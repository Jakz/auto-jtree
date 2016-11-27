package com.jack.autotree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.jack.autotree.builders.*;
import com.jack.autotree.builders.array.TreeBuilderObjectArray;
import com.jack.autotree.builders.array.TreeBuilderPrimitiveArray;
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
import com.jack.autotree.types.TypeMapper;

public class AutoTreeBuilder
{
  final private TypeMapper<AutoType, TreeBuilder> builders;
   
  final private List<Instancer> instancers;
  final private AutoTreeContext context;
  
  private void registerDefaultBuilder(Class<?> type, TreeBuilder builder)
  {
    builders.registerDefault(new RawType(type), builder);
  }
  
  private void registerBuilder(Class<?> type, TreeBuilder builder)
  {
    builders.register(new RawType(type), builder);
  }
  
  public void registerHierarchyBuilder(AutoType type, TreeBuilder builder)
  {
    builders.registerHierarchy(type, builder);
  }
  
  public void registerDefaultHierarchyBuilder(AutoType type, TreeBuilder builder)
  {
    builders.registerHierarchyDefault(type, builder);
  }
  
  private void registerStandardBuilders()
  {
    registerDefaultBuilder(Float.TYPE, new FloatTreeBuilder());
    registerDefaultBuilder(Float.class, new FloatTreeBuilder());
    registerDefaultBuilder(Double.TYPE, new DoubleTreeBuilder());
    registerDefaultBuilder(Double.class, new DoubleTreeBuilder());
    registerDefaultBuilder(Integer.TYPE, new IntegerTreeBuilder());
    registerDefaultBuilder(Integer.class, new IntegerTreeBuilder());
    registerDefaultBuilder(Byte.TYPE, new ByteTreeBuilder());
    registerDefaultBuilder(Byte.class, new ByteTreeBuilder());
    registerDefaultBuilder(Short.TYPE, new ShortTreeBuilder());
    registerDefaultBuilder(Short.class, new ShortTreeBuilder());
    registerDefaultBuilder(Character.TYPE, new CharacterTreeBuilder());
    registerDefaultBuilder(Character.class, new CharacterTreeBuilder());
    registerDefaultBuilder(Long.TYPE, new LongTreeBuilder());
    registerDefaultBuilder(Long.class, new LongTreeBuilder());
    registerDefaultBuilder(Boolean.TYPE, new BooleanTreeBuilder());
    registerDefaultBuilder(Boolean.class, new BooleanTreeBuilder());
    registerDefaultBuilder(String.class, new StringTreeBuilder());
    
    registerHierarchyBuilder(new RawType(Object[].class), new TreeBuilderObjectArray());
    registerDefaultBuilder(boolean[].class, new TreeBuilderPrimitiveArray(Boolean.TYPE));
    registerDefaultBuilder(byte[].class, new TreeBuilderPrimitiveArray(Byte.TYPE));
    registerDefaultBuilder(short[].class, new TreeBuilderPrimitiveArray(Short.TYPE));
    registerDefaultBuilder(char[].class, new TreeBuilderPrimitiveArray(Character.TYPE));
    registerDefaultBuilder(int[].class, new TreeBuilderPrimitiveArray(Integer.TYPE));
    registerDefaultBuilder(long[].class, new TreeBuilderPrimitiveArray(Long.TYPE));
    registerDefaultBuilder(float[].class, new TreeBuilderPrimitiveArray(Float.TYPE));
    registerDefaultBuilder(double[].class, new TreeBuilderPrimitiveArray(Double.TYPE));

    registerDefaultHierarchyBuilder(new RawType(Enum.class), new TreeBuilderEnum());
    registerDefaultHierarchyBuilder(new RawType(List.class), new TreeBuilderList());
    
    builders.setDefault(new TreeBuilderReflective());
    
    instancers.add(Instancer.PRIMITIVE_INSTANCER);
    instancers.add(Instancer.ARRAY_INSTANCER);
    instancers.add(Instancer.ENUM_INSTANCER);
    instancers.add(Instancer.DEFAULT_INSTANCER);
  }
  
  public AutoTreeBuilder()
  {
    builders = new TypeMapper<>();
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
    TreeBuilder builder = builders.get(type);
    return builder.build(o, type, context);
  }
  
  public Object instantiate(Class<?> clazz)
  {
    Object object = null;
    
    for (int i = 0; i < instancers.size() && object == null; ++i)
      object = instancers.get(i).instantiate(clazz);
    
    return object;
  }

}
