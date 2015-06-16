package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.ShortNode;
import com.jack.autotree.nodes.TreeNode;

public class ShortTreeBuilder implements TreeBuilder<Short>
{
  public TreeNode build(Short source, AutoTreeContext context)
  {
    return new ShortNode(context.peek());
  }
}