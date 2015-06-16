package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.BooleanNode;
import com.jack.autotree.nodes.TreeNode;

public class BooleanTreeBuilder implements TreeBuilder<Boolean>
{
  public TreeNode build(Boolean source, AutoTreeContext context)
  {
    return new BooleanNode(context.peek());
  }
}