package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.LongNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class LongTreeBuilder implements TreeBuilder<Long>
{
  public AutoTreeNode build(Long source, AutoTreeContext context)
  {
    return new LongNode(context.generator(), context.peek());
  }
}