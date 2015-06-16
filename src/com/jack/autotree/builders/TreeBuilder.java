package com.jack.autotree.builders;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.TreeNode;

public interface TreeBuilder<T>
{
  TreeNode build(T source, AutoTreeContext context);
}