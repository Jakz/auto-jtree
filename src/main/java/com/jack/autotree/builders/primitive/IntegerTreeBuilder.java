package com.jack.autotree.builders.primitive;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.builders.TreeBuilder;
import com.jack.autotree.nodes.IntegerNode;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.nodes.AutoTreeNode;

public class IntegerTreeBuilder implements TreeBuilder
{
  public AutoTreeNode build(Object source, AutoType type, AutoTreeContext context)
  {
    return new IntegerNode(context.generator(), context.peek());
  }
}