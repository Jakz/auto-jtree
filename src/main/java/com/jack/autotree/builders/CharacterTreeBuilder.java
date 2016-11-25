package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.CharacterNode;
import com.jack.autotree.nodes.AutoTreeNode;

public class CharacterTreeBuilder implements TreeBuilder<Character>
{
  public AutoTreeNode build(Character source, AutoTreeContext context)
  {
    return new CharacterNode(context.generator(), context.peek());
  }
}