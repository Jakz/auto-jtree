package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.StringNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class StringTreeBuilder implements TreeBuilder<String>
{
  public AutoTreeNode build(String source, AutoTreeContext context)
  {
    return new StringNode(context.generator(), context.peek());
  }
}