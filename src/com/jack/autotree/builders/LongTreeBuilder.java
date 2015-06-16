package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.LongNode;
import com.jack.autotree.nodes.TreeNode;

public class LongTreeBuilder implements TreeBuilder<Long>
{
  public TreeNode build(Long source, AutoTreeContext context)
  {
    return new LongNode(context.peek());
  }
}