package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.CharacterNode;
import com.jack.autotree.nodes.TreeNode;

public class CharacterTreeBuilder implements TreeBuilder<Character>
{
  public TreeNode build(Character source, AutoTreeContext context)
  {
    return new CharacterNode(context.peek());
  }
}