package com.jack.autojtree.tests;

import static com.jack.autojtree.tests.Helper.assertTreeEquals;


import javax.swing.tree.TreeModel;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;

import com.jack.autojtree.tests.Helper.ClassNode;
import com.jack.autotree.AutoTree;
import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.InnerObjectNode;
import com.jack.autotree.nodes.primitive.IntegerNode;

import org.junit.Assert;
import org.junit.BeforeClass;

/**
 * Unit test for simple App.
 */
public class TestModifiers 
{
  public static class TestPublicStatic
  {
    public static int x;
    public int y = 5;
  }
  
  @Test
  public void testPublicStaticFieldShouldNotBeSerialized()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(new TestPublicStatic(), TestPublicStatic.class);
    
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, IntegerNode.class));
  }
  
  public static class TestPrivateStatic
  {
    @SuppressWarnings("unused")
    private static int x = 10;
    public int y = 5;
  }
  
  @Test
  public void testPrivateStaticFieldShouldNotBeSerialized()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(new TestPrivateStatic(), TestPrivateStatic.class);
    
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, IntegerNode.class));
  }
  
  public static class TestPrivateFinalStatic
  {
    @SuppressWarnings("unused")
    private static final int x = 10;
    public int y = 5;
  }
  
  @Test
  public void testPrivateFinalStaticFieldShouldNotBeSerialized()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(new TestPrivateStatic(), TestPrivateStatic.class);
    
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, IntegerNode.class));
  }
}
