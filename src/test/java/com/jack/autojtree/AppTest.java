package com.jack.autojtree;

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

import com.jack.autotree.AutoTree;
import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.IntegerNode;

import org.junit.Assert;
import org.junit.BeforeClass;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
  @Test
  public void testPrimitiveInt()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(new Integer(10), Integer.class);
    
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertNotNull(model.getRoot());
    assertEquals(model.getChildCount(root), 0);
    assertThat(root, instanceOf(IntegerNode.class));
  }
  
  @Test
  public void testModifyPrimitiveField()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    Rectangle rect = new Rectangle(10,20,10,20);
    TreeModel model = builder.generate(rect, Rectangle.class);

    AutoTreeNode<?> root = (AutoTreeNode<?>)model.getRoot();
    root.getChildAt(0).setUserObject(30);
    
    assertEquals(rect.x, 30);
  }
  
  @Test
  public void testCompositeWithPrimitiveObject()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(new Rectangle(10,20,10,20), Rectangle.class);
    
    AutoTreeNode<?> root = (AutoTreeNode<?>)model.getRoot();
    
    assertNotNull(model.getRoot());
    assertEquals(model.getChildCount(root), 4);
    assertThat(root, instanceOf(InnerNode.class));
    
    for (AutoTreeNode<?> child : root)
      assertThat(child, instanceOf(IntegerNode.class));
  }
  
  public static class RectangleCouple
  {
    public Rectangle r1;
    public Rectangle r2;
  }
  
  @Test
  public void testCompositeNestedWithPrimitiveObject()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    
    RectangleCouple rc = new RectangleCouple();
    rc.r1 = new Rectangle();
    rc.r2 = new Rectangle();
    
    TreeModel model = builder.generate(rc, RectangleCouple.class);
    
    AutoTreeNode<?> root = (AutoTreeNode<?>)model.getRoot();
    
    assertNotNull(model.getRoot());
    assertEquals(model.getChildCount(root), 2);
    assertThat(root, instanceOf(InnerNode.class));
    
    for (AutoTreeNode<?> child : root)
    {
      assertThat(child, instanceOf(InnerNode.class));
      assertEquals(child.getChildCount(), 4);
      for (AutoTreeNode<?> innerChild : child)
      {
        assertThat(innerChild, instanceOf(IntegerNode.class));
      }
    }
  }
}
