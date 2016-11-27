package com.jack.autotree.builders;

import java.util.List;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.proxies.ListProxy;
import com.jack.autotree.proxies.ValueProxy;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.types.GenericType;

public class TreeBuilderList extends TreeBuilderGeneric
{
  public TreeBuilderList() { }

  @Override public AutoTreeNode build(Object object, AutoType type, AutoTreeContext context)
  {
    List<?> source = (List<?>)object;
    GenericType gtype = (GenericType)type;

    ValueProxy proxy = context.peek();
    InnerListNode node = new InnerListNode(context.generator(), proxy, proxy.mnemonic(), source, gtype);

    for (int i = 0; i < source.size(); ++i)
    {
      context.push(new ListProxy(context.peek(), node, i, true));
      node.add(context.build(source.get(i), gtype.getTypeArgument(0)));
      context.pop();
    }
    
    return node;
  }
}