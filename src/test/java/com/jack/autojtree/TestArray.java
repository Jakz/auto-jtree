package com.jack.autojtree;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.tree.TreeModel;

import org.junit.Test;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerArrayNode;
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.nodes.IntegerNode;

public class TestArray
{
  @Test
  public void testBuilding()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    Integer[] array = new Integer[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(array, Integer[].class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();

    assertNotNull(model.getRoot());
    assertThat(root, instanceOf(InnerArrayNode.class));
    assertEquals(model.getChildCount(root), 5);
    assertThat(root.getChildAt(0), instanceOf(IntegerNode.class));
  }

  @Test
  public void testModifyElement()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    Integer[] array = new Integer[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(array, Integer[].class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(1).setUserObject(10);
    
    assertEquals(array[1], new Integer(10));  
  }
}
