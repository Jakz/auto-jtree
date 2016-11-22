package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.InnerArrayNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.*;
import java.lang.reflect.ParameterizedType;

public class TreeBuilderArray<T, K> extends TreeBuilderGeneric<T, K>
{
  @SuppressWarnings("unchecked")
  public TreeBuilderArray(Class<K> clazz)
  {
    super(clazz);
    //super.setClass((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public AutoTreeNode build(T source, AutoTreeContext context)
  {    
    ValueProxy proxy = context.peek();

    InnerNode node = new InnerArrayNode(proxy, proxy.mnemonic(), source, source.getClass());

    K[] casted = (K[])(source);
    
    for (int i = 0; i < casted.length; ++i)
    {
       context.push(new ArrayProxy(proxy, node, i, true));
       node.add(context.build(casted[i], getClazz().getComponentType()));
       context.pop();
    }
    
    return node;
  }
}