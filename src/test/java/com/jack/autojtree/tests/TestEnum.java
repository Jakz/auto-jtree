package com.jack.autojtree.tests;

import javax.swing.tree.TreeModel;

import org.junit.Test;

import static com.jack.autojtree.tests.Helper.assertTreeEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jack.autojtree.tests.Helper.ClassNode;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.nodes.EnumNode;
import com.jack.autotree.nodes.InnerArrayNode;
import com.jack.autotree.nodes.InnerListNode;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.InnerObjectNode;
import com.jack.autotree.types.GenericType;

public class TestEnum
{
  public static enum Enumeration
  {
    VALUE1,
    VALUE2,
    VALUE3,
    VALUE4
  }
  
  @Test
  public void testBuilding()
  {
    Enumeration object = Enumeration.VALUE2;
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(object, Enumeration.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(EnumNode.class));
  }
  
  public static class Wrapper
  {
    public Enumeration object;
  }
  
  @Test
  public void testBuildingAsMember()
  {
    Wrapper object = new Wrapper();
    object.object = Enumeration.VALUE3;
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(object, Wrapper.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, EnumNode.class));
  }
  
  @Test
  public void testBuildingAsArray()
  {
    Enumeration[] object = new Enumeration[] { Enumeration.VALUE1, Enumeration.VALUE4 };
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(object, Enumeration[].class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerArrayNode.class, EnumNode.class, 2));
  }
  
  @Test
  public void testBuildingAsList()
  {
    List<Enumeration> object = Arrays.asList(Enumeration.VALUE1, Enumeration.VALUE4);
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(object, new GenericType(List.class, Enumeration.class)); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    assertTreeEquals(root, new ClassNode(InnerListNode.class, EnumNode.class, 2));
  }
  
  @Test
  public void testModifyAsMember()
  {
    Wrapper object = new Wrapper();
    object.object = Enumeration.VALUE3;
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(object, Wrapper.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).setUserObject(Enumeration.VALUE4);
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, EnumNode.class));
    assertEquals(object.object, Enumeration.VALUE4);
  }
  
  public static class ArrayWrapper
  {
    public Enumeration[] values;
  }
  
  @Test
  public void testAddElementArray()
  {
    ArrayWrapper wrapper = new ArrayWrapper();
    wrapper.values = new Enumeration[] { Enumeration.VALUE1, Enumeration.VALUE4 };
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(wrapper, ArrayWrapper.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).addElement(0);
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, new ClassNode(InnerArrayNode.class, EnumNode.class, 3)));
  }
  
  public static class ListWrapper
  {
    public List<Enumeration> values;
  }
  
  @Test
  public void testAddElementList()
  {
    ListWrapper wrapper = new ListWrapper();
    wrapper.values = new ArrayList<>(Arrays.asList(Enumeration.VALUE1, Enumeration.VALUE4));
    
    AutoTreeBuilder builder = new AutoTreeBuilder();
    TreeModel model = builder.generate(wrapper, ListWrapper.class); 
    AutoTreeNode root = (AutoTreeNode)model.getRoot();
    
    root.getChildAt(0).addElement(0);
    
    assertTreeEquals(root, new ClassNode(InnerObjectNode.class, new ClassNode(InnerListNode.class, EnumNode.class, 3)));
  }

}
