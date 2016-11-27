package com.jack.autotree.builders.primitive;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.builders.TreeBuilder;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.primitive.BooleanNode;

public class BooleanTreeBuilder implements TreeBuilder
{
  public AutoTreeNode build(Object source, AutoType type, AutoTreeContext context)
  {
    return new BooleanNode(context.generator(), context.peek());
  }
}