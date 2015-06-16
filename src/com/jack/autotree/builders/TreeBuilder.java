package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.AutoTreeNode;

public interface TreeBuilder<T>
{
  AutoTreeNode build(T source, AutoTreeContext context);
}