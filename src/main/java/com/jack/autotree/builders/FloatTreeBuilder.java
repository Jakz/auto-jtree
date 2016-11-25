package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.FloatNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class FloatTreeBuilder implements TreeBuilder<Float>
{
  public AutoTreeNode build(Float source, AutoTreeContext context)
  {
    return new FloatNode(context.generator(), context.peek());
  }
}