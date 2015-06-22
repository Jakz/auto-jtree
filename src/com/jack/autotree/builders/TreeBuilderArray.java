package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.ArrayProxy;
import com.jack.autotree.proxies.FieldProxy;

public class TreeBuilderArray<T> implements TreeBuilder<T[]>
{
  public AutoTreeNode build(T[] source, AutoTreeContext context)
  {
    InnerNode node = null;
    
    if (context.peek() instanceof FieldProxy)
      node = new InnerNode(((FieldProxy)context.peek()).mnemonic(), source);
    else
      node = new InnerNode(source);
    
    for (int i = 0; i < source.length; ++i)
    {
      context.push(new ArrayProxy(source, i, true));
      node.add(context.build(source[i]));
      context.pop();
    }
    
    return node;
  }
}