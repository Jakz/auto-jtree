package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class InnerObjectNode extends InnerNode
{
  public InnerObjectNode(AutoTreeBuilder builder, ValueProxy proxy, Object object)
  {
    super(builder, proxy, object);
  }
}
