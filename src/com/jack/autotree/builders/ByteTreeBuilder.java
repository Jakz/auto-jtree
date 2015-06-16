package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.ByteNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class ByteTreeBuilder implements TreeBuilder<Byte>
{
  public AutoTreeNode build(Byte source, AutoTreeContext context)
  {
    return new ByteNode(context.peek());
  }
}