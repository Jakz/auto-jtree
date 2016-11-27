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

public class TreeBuilderIntArray extends TreeBuilderGeneric
{
  public TreeBuilderIntArray()
  {
  }
  
  @Override
  public AutoTreeNode build(Object object, AutoType type, AutoTreeContext context)
  {
    int[] source = (int[]) object;
    ValueProxy proxy = context.peek();

    InnerNode node = new InnerArrayNode(context.generator(), proxy, proxy.mnemonic(), source, source.getClass());

    for (int i = 0; i < source.length; ++i)
    {
       context.push(new ArrayProxy(proxy, node, i, true));
       node.add(context.build(source[i], new RawType(Integer.TYPE)));
       context.pop();
    }
    
    return node;
  }
}