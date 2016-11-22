package com.jack.autojtree;

import javax.swing.tree.TreeModel;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.jack.autotree.AutoTree;

import org.junit.BeforeClass;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
 
  @Test
  public void testPrimitiveInt()
  {
    AutoTree tree = new AutoTree();
    tree.generate(new Integer(10), Integer.class);
    TreeModel model = tree.getModel();
    
    Object root = model.getRoot();
    
    assertNotNull(model.getRoot());
    assertEquals(model.getChildCount(root), 0);
  }
}
