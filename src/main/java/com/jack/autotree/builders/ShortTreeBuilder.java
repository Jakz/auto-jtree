package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.ShortNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class ShortTreeBuilder implements TreeBuilder<Short>
{
  public AutoTreeNode build(Short source, AutoTreeContext context)
  {
    return new ShortNode(context.generator(), context.peek());
  }
}