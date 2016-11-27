package com.jack.autojtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerArrayNode;
import com.jack.autotree.nodes.InnerObjectNode;
import com.jack.autotree.nodes.primitive.FloatNode;
import com.jack.autotree.nodes.primitive.IntegerNode;

public class Helper
{
  public static class ClassNode
  {
    final Class<? extends AutoTreeNode> clazz;
    final ClassNode[] children;
    
    ClassNode(Class<? extends AutoTreeNode> clazz)
    {
      this.clazz = clazz;
      children = new ClassNode[0];
    }
    
    ClassNode(Class<? extends AutoTreeNode> clazz, ClassNode... children)
    {
      this.clazz = clazz;
      this.children = children;
    }
    
    ClassNode(Class<? extends AutoTreeNode> clazz, ClassNode child, int count)
    {
      this.clazz = clazz;
      this.children = Collections.nCopies(count, child).toArray(new ClassNode[count]);
    }
    
    @SafeVarargs
    ClassNode(Class<? extends AutoTreeNode> clazz, Class<? extends AutoTreeNode>... children)
    {
      this.clazz = clazz;
      this.children = Arrays.stream(children)
          .map(ClassNode::new)
          .toArray(i -> new ClassNode[i]);
    }
    
    ClassNode(Class<? extends AutoTreeNode> clazz, Class<? extends AutoTreeNode> child, int count)
    {
      this.clazz = clazz;
      this.children = Collections.nCopies(count, child).stream()
          .map(ClassNode::new)
          .toArray(i -> new ClassNode[i]);
    }
  }
  
  public static boolean assertEquals(AutoTreeNode node, ClassNode tree)
  {
    boolean valid = node.getChildCount() == tree.children.length &&
        node.getClass() == tree.clazz;
    
    if (valid)
    {
      for (int i = 0; i < node.getChildCount(); ++i)
        if (!assertEquals(node.getChildAt(i), tree.children[i]))
          return false;
      
      return true;
    }
    
    return false;
  }
}
