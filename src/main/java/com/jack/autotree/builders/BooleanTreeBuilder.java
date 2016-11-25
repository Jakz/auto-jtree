package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.BooleanNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class BooleanTreeBuilder implements TreeBuilder<Boolean>
{
  public AutoTreeNode build(Boolean source, AutoTreeContext context)
  {
    return new BooleanNode(context.generator(), context.peek());
  }
}