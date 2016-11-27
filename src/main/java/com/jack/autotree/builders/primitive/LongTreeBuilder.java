package com.jack.autotree.builders.primitive;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.builders.TreeBuilder;
import com.jack.autotree.nodes.LongNode;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.nodes.AutoTreeNode;

public class LongTreeBuilder implements TreeBuilder
{
  public AutoTreeNode build(Object source, AutoType type, AutoTreeContext context)
  {
    return new LongNode(context.generator(), context.peek());
  }
}