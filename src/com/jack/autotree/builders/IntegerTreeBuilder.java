package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.IntegerNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class IntegerTreeBuilder implements TreeBuilder<Integer>
{
  public AutoTreeNode build(Integer source, AutoTreeContext context)
  {
    return new IntegerNode(context.peek());
  }
}