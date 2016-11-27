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

public class TreeBuilderObjectArray extends TreeBuilderGeneric
{
  public TreeBuilderObjectArray()
  {
  }
  
  @Override
  public AutoTreeNode build(Object source, AutoType type, AutoTreeContext context)
  {    
    Class<?> clazz = type.get();
    ValueProxy proxy = context.peek();

    InnerNode node = new InnerArrayNode(context.generator(), proxy, proxy.mnemonic(), source, source.getClass());

    for (int i = 0; i < Array.getLength(source); ++i)
    {
       context.push(new ArrayProxy(proxy, node, i, true));
       node.add(context.build(Array.get(source, i), new RawType(clazz.getComponentType())));
       context.pop();
    }
    
    return node;
  }
}