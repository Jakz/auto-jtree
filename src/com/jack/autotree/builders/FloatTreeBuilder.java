package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.FloatNode;
import com.jack.autotree.nodes.TreeNode;

public class FloatTreeBuilder implements TreeBuilder<Float>
{
  public TreeNode build(Float source, AutoTreeContext context)
  {
    return new FloatNode(context.peek());
  }
}