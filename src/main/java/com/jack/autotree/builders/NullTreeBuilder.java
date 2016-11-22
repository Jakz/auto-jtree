package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.ValueProxy;
import com.jack.autotree.nodes.*;

public class NullTreeBuilder<T> implements TreeBuilder<T>
{
  private final Class<T> clazz;
  
  public NullTreeBuilder(Class<T> clazz)
  {
    this.clazz = clazz;
  }
  
  public AutoTreeNode build(T source, AutoTreeContext context)
  {
    ValueProxy proxy = context.peek();
    return new NullNode(proxy, proxy.mnemonic(), clazz);
  }
}
