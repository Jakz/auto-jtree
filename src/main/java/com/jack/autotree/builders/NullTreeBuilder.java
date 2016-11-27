package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.ValueProxy;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.nodes.*;

public class NullTreeBuilder implements TreeBuilder
{  
  public NullTreeBuilder()
  {
  }
  
  public AutoTreeNode build(Object source, AutoType type, AutoTreeContext context)
  {
    ValueProxy proxy = context.peek();
    return new NullNode(context.generator(), proxy, proxy.mnemonic(), type);
  }
}
