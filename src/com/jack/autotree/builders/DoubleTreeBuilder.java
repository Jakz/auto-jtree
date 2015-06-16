package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.DoubleNode;
import com.jack.autotree.nodes.TreeNode;

public class DoubleTreeBuilder implements TreeBuilder<Double>
{
  public TreeNode build(Double source, AutoTreeContext context)
  {
    return new DoubleNode(context.peek());
  }
}