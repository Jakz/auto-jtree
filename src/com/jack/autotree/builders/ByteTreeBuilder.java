package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.ByteNode;
import com.jack.autotree.nodes.TreeNode;

public class ByteTreeBuilder implements TreeBuilder<Byte>
{
  public TreeNode build(Byte source, AutoTreeContext context)
  {
    return new ByteNode(context.peek());
  }
}