package com.jack.autojtree.tests;

import static com.jack.autojtree.tests.Helper.assertTreeEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.swing.tree.TreeModel;

import org.junit.Test;

import com.jack.autojtree.tests.Helper.ClassNode;
import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.annotations.Hidden;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.InnerObjectNode;
import com.jack.autotree.nodes.primitive.IntegerNode;

public class TestAnnotations
{
  public static class HiddenTest
  {
    @Hidden public int x;
    public int y;
  }
  
  public static class AllHiddenTest
  {
    @Hidden public int x;
    @Hidden public int y;
  }
  
  @Test
  public void testHiddenFieldShouldNotBeGenerated()
  {
    HiddenTest object = new HiddenTest();
    object.x = 10;
    object.y = 5;
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(object, HiddenTest.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, IntegerNode.class));
  }
  
  @Test
  public void testObjectWithAllHiddenFieldsShuoldBeLeaf()
  {
    AllHiddenTest object = new AllHiddenTest();
    object.x = 10;
    object.y = 5;
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(object, AllHiddenTest.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class));
    assertTrue(root.isLeaf());
  }
}
