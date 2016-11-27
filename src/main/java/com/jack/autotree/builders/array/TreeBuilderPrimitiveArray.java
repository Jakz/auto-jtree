package com.jack.autotree.builders.array;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.builders.TreeBuilderGeneric;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.InnerArrayNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.*;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.types.RawType;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

public class TreeBuilderPrimitiveArray extends TreeBuilderGeneric
{
  private final Class<?> componentType;
  
  public TreeBuilderPrimitiveArray(Class<?> componentType)
  {
    this.componentType = componentType;
  }
  
  @Override
  public AutoTreeNode build(Object object, AutoType type, AutoTreeContext context)
  {
    ValueProxy proxy = context.peek();
    InnerNode node = new InnerArrayNode(context.generator(), proxy, proxy.mnemonic(), object, object.getClass());
    
    for (int i = 0; i < Array.getLength(object); ++i)
    {
       context.push(new ArrayProxy(proxy, node, i, true));
       node.add(context.build(Array.get(object, i), new RawType(componentType)));
       context.pop();
    }
    
    return node;
  }
}