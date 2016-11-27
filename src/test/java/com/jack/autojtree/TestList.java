package com.jack.autojtree;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static com.jack.autojtree.Helper.assertEquals;
import com.jack.autojtree.Helper.ClassNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.tree.TreeModel;

import org.junit.Test;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.nodes.primitive.IntegerNode;
import com.jack.autotree.types.GenericType;

public class TestList
{
  @Test
  public void testBuilding()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    TreeModel model = builder.generate(list, new GenericType(List.class, Integer.class)); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();

    assertNotNull(root);
    assertEquals(root, new ClassNode(InnerListNode.class, IntegerNode.class, 5));
  }
  
  @Test
  public void testClearing()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    TreeModel model = builder.generate(list, new GenericType(List.class, Integer.class)); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.clear();
    
    assertEquals(model.getChildCount(root), 0);
    assertTrue(list.isEmpty());
  }
  
  @Test
  public void testModifyElement()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    TreeModel model = builder.generate(list, new GenericType(List.class, Integer.class)); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(1).setUserObject(10);
    
    assertEquals(list.size(), 5);
    assertEquals(list.get(1), new Integer(10));  
  }
}
