package com.jack.autojtree;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.tree.TreeModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerArrayNode;
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.nodes.IntegerNode;

public class TestArray
{
  @Rule
  public final ExpectedException exception = ExpectedException.none();
  
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
  

  
  @Test
  public void testAddElementShouldFailForRootNodes()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    Integer[] array = new Integer[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(array, Integer[].class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    exception.expect(UnsupportedOperationException.class);
    root.addElement(0);
    //assertEquals();  
  }
  
  public static class IntegerArrayHolder
  {
    public Integer[] array;
  }
  
  @Test
  public void testAddElementToBeginOfComponentArray()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    IntegerArrayHolder holder = new IntegerArrayHolder();
    holder.array = new Integer[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(holder, IntegerArrayHolder.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).addElement(0);
    assertArrayEquals(new Integer[]{ null, 1, 2, 3, 4, 5 }, holder.array);
    //assertEquals();  
  }
  
  @Test
  public void testAddElementToEndOfComponentArray()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    IntegerArrayHolder holder = new IntegerArrayHolder();
    holder.array = new Integer[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(holder, IntegerArrayHolder.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).addElement(holder.array.length-1);
    assertArrayEquals(new Integer[]{ 1, 2, 3, 4, 5, null }, holder.array);
    //assertEquals();  
  }
  
  @Test
  public void testAddElementInMiddleOfComponentArray()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    IntegerArrayHolder holder = new IntegerArrayHolder();
    holder.array = new Integer[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(holder, IntegerArrayHolder.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).addElement(1);
    assertArrayEquals(new Integer[]{ 1, null, 2, 3, 4, 5 }, holder.array);
    //assertEquals();  
  }
}
