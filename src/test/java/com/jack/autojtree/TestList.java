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
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.nodes.IntegerNode;

public class TestList
{
  @Test
  public void testBuilding()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    TreeModel model = builder.generate(list, List.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();

    assertNotNull(model.getRoot());
    assertThat(root, instanceOf(InnerListNode.class));
    assertEquals(model.getChildCount(root), 5);
    assertThat(root.getChildAt(0), instanceOf(IntegerNode.class));
  }
  
  @Test
  public void testClearing()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    TreeModel model = builder.generate(list, List.class); 
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
    TreeModel model = builder.generate(list, List.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(1).setUserObject(10);
    
    assertEquals(list.size(), 5);
    assertEquals(list.get(1), new Integer(10));  
  }
}
