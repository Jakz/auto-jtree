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
  public void testAddElement()
  {
    AutoTreeBuilder builder = new AutoTreeBuilder();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    TreeModel model = builder.generate(list, List.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.addElement(0);
    
    assertEquals(model.getChildCount(root), 6);  }
}
