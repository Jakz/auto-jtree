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
import java.util.stream.IntStream;

import javax.swing.tree.TreeModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerArrayNode;
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.nodes.primitive.IntegerNode;

public class TestArray
{
  @Rule
  public final ExpectedException exception = ExpectedException.none();
  
  public static class IntegerArrayHolder
  {
    public Integer[] array;
  }
  
  public Pair<AutoTreeNode, IntegerArrayHolder> build(int... values)
  {
    IntegerArrayHolder holder = new IntegerArrayHolder();
    holder.array = IntStream.of(values).boxed().toArray(i -> new Integer[i]);
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(holder, IntegerArrayHolder.class); 
    AutoTreeNode root = (AutoTreeNode )model.getRoot();
    
    return new Pair<>(root, holder);
  }
  
  @Test
  public void testBuilding()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    Integer[] array = new Integer[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(array, Integer[].class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();

    assertNotNull(model.getRoot());
    assertThat(root, instanceOf(InnerArrayNode.class));
    assertEquals(root.getChildCount(), 5);
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
    
  @Test
  public void testAddElementToBeginOfComponentArray()
  {
    Pair<AutoTreeNode, IntegerArrayHolder> pair = build(1,2,3,4,5);

    pair.first.getChildAt(0).addElement(0);
    assertArrayEquals(new Integer[]{ 0, 1, 2, 3, 4, 5 }, pair.second.array);
    //assertEquals();  
  }
  
  @Test
  public void testAddElementToEndOfComponentArray()
  {
    Pair<AutoTreeNode, IntegerArrayHolder> pair = build(1,2,3,4,5);

    pair.first.getChildAt(0).addElement(pair.second.array.length);
    assertArrayEquals(new Integer[]{ 1, 2, 3, 4, 5, 0 }, pair.second.array);
  }
  
  @Test
  public void testAddElementInMiddleOfComponentArray()
  {
    Pair<AutoTreeNode, IntegerArrayHolder> pair = build(1,2,3,4,5);

    pair.first.getChildAt(0).addElement(1);
    assertArrayEquals(new Integer[]{ 1, 0, 2, 3, 4, 5 }, pair.second.array);
  }
  
  @Test
  public void testRemoveElementToBeginOfComponentArray()
  {
    Pair<AutoTreeNode, IntegerArrayHolder> pair = build(1,2,3,4,5);

    pair.first.getChildAt(0).removeElement(0);
    assertArrayEquals(new Integer[]{ 2, 3, 4, 5 }, pair.second.array);
  }
  
  @Test
  public void testRemoveElementToEndOfComponentArray()
  {
    Pair<AutoTreeNode, IntegerArrayHolder> pair = build(1,2,3,4,5);

    pair.first.getChildAt(0).removeElement(4);
    assertArrayEquals(new Integer[]{ 1, 2, 3, 4 }, pair.second.array);
  }
  
  @Test
  public void testRemoveElementToMiddleOfComponentArray()
  {
    Pair<AutoTreeNode, IntegerArrayHolder> pair = build(1,2,3,4,5);

    pair.first.getChildAt(0).removeElement(3);
    assertArrayEquals(new Integer[]{ 1, 2, 3, 5 }, pair.second.array);
  }
  
  @Test
  public void testBuildingPrimitiveInt()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    int[] array = new int[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(array, int[].class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertNotNull(model.getRoot());
    assertThat(root, instanceOf(InnerArrayNode.class));
    assertEquals(root.getChildCount(), 5);
    assertThat(root.getChildAt(0), instanceOf(IntegerNode.class));
  }
  
  @Test
  public void testModifyElementPrimitiveInt()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    int[] array = new int[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(array, int[].class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(1).setUserObject(10);
    
    assertEquals(array[1], 10);  
  }
  
  public static class PrimitiveIntHolder
  {
    public int[] array;
  }
  
  @Test
  public void testAddElementPrimitiveInt()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    PrimitiveIntHolder holder = new PrimitiveIntHolder();
    holder.array = new int[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(holder, PrimitiveIntHolder.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).addElement(0);
    
    assertArrayEquals(holder.array, new int[] { 0, 1, 2, 3, 4, 5});  
  }
  
  @Test
  public void testRemoveElementPrimitiveInt()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    PrimitiveIntHolder holder = new PrimitiveIntHolder();
    holder.array = new int[]{ 1, 2, 3, 4, 5 };
    TreeModel model = builder.generate(holder, PrimitiveIntHolder.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).removeElement(0);
    assertArrayEquals(holder.array, new int[] { 2, 3, 4, 5 });  
  }
    
  @Test
  public void testBuildingPrimitiveBidimensionalInt()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    int[][] array = new int[][]{new int[]{ 1, 2, 3, 4, 5 }, new int[]{ 6, 7, 8, 9, 0}};
    TreeModel model = builder.generate(array, int[][].class); 
    
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    AutoTreeNode child = (AutoTreeNode)root.getChildAt(0);
    
    assertNotNull(model.getRoot());
    assertThat(root, instanceOf(InnerArrayNode.class));
    
    assertEquals(root.getChildCount(), 2);
    assertThat(root.getChildAt(0), instanceOf(InnerArrayNode.class));
    
    assertEquals(child.getChildCount(), 5);
    assertThat(child.getChildAt(0), instanceOf(IntegerNode.class));
  }
  
  public static class PrimitiveBidimensionalIntHolder
  {
    public int[][] array;
  }
  
  @Test
  public void testAddElementPrimitiveBidimensionalInt()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    PrimitiveBidimensionalIntHolder holder = new PrimitiveBidimensionalIntHolder();
    holder.array = new int[][]{new int[]{ 1, 2, 3, 4, 5 }, new int[]{ 6, 7, 8, 9, 0}};
    TreeModel model = builder.generate(holder, PrimitiveBidimensionalIntHolder.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).addElement(0);
    
    assertEquals(holder.array.length, 3);  
    assertArrayEquals(holder.array[0], new int[] { });
  }
}
