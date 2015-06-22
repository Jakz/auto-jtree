package com.jack.autotree.builders;

import java.util.List;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.FieldProxy;
import com.jack.autotree.proxies.ListProxy;

public class TreeBuilderList<T> implements TreeBuilder<List<T>>
{
  public AutoTreeNode build(List<T> source, AutoTreeContext context)
  {
    InnerNode node = null;
    
    if (context.peek() instanceof FieldProxy)
      node = new InnerNode(((FieldProxy)context.peek()).mnemonic(), source);
    else
      node = new InnerNode(source);
    
    for (int i = 0; i < source.size(); ++i)
    {
      context.push(new ListProxy(source, i, true));
      node.add(context.build(source.get(i)));
      context.pop();
    }
    
    return node;
  }
}