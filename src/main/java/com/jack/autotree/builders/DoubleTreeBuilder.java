package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.DoubleNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class DoubleTreeBuilder implements TreeBuilder<Double>
{
  public AutoTreeNode build(Double source, AutoTreeContext context)
  {
    return new DoubleNode(context.generator(), context.peek());
  }
}