package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.IntegerNode;
import com.jack.autotree.nodes.TreeNode;

public class IntegerTreeBuilder implements TreeBuilder<Integer>
{
  public TreeNode build(Integer source, AutoTreeContext context)
  {
    return new IntegerNode(context.peek());
  }
}