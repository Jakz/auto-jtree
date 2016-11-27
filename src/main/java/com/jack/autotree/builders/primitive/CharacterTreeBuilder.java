package com.jack.autotree.builders.primitive;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.builders.TreeBuilder;
import com.jack.autotree.nodes.CharacterNode;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.nodes.AutoTreeNode;

public class CharacterTreeBuilder implements TreeBuilder
{
  public AutoTreeNode build(Object source, AutoType type, AutoTreeContext context)
  {
    return new CharacterNode(context.generator(), context.peek());
  }
}