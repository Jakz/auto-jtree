package com.jack.autotree.builders;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.proxies.FieldProxy;
import com.jack.autotree.proxies.ListProxy;
import com.jack.autotree.proxies.ValueProxy;

public class TreeBuilderList<T> extends TreeBuilderGeneric<List<T>, T>
{
  public TreeBuilderList(Class<T> clazz)
  {
    super(clazz);
    //super.setClass((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
  }
  
  public AutoTreeNode build(List<T> source, AutoTreeContext context)
  {
    ValueProxy proxy = context.peek();
    InnerNode node = new InnerListNode(context.generator(), proxy, proxy.mnemonic(), source, getClazz());

    for (int i = 0; i < source.size(); ++i)
    {
      context.push(new ListProxy(context.peek(), node, i, true));
      node.add(context.build(source.get(i), getClazz()));
      context.pop();
    }
    
    return node;
  }
}