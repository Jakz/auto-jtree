package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.types.AutoType;

public interface TreeBuilder
{
  AutoTreeNode build(Object source, AutoType type, AutoTreeContext context);
}