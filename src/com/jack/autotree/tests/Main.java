package com.jack.autotree.tests;

import java.util.*;
import javax.swing.*;

import com.jack.autotree.annotations.*;
import com.jack.autotree.AutoTree;

public class Main
{
	public static class Inner2
	{
	  public Boolean x[] = new Boolean[]{true, false, false};
	  public int t = 20;
	}
  
  public static class Inner
	{
    public List<Double> values;
    public Inner2 inner[] = new Inner2[] { new Inner2(), new Inner2() };
	  
	  Inner()
	  {
	    values = new ArrayList<Double>();
	    values.add(10.0);
	    values.add(20.0);
	  }
	}
  
  public static class Test
	{
	  public float fx = 20.0f;
	  public Float fy = 30.0f;

	  public boolean bx = false;
	  public Boolean by = Boolean.TRUE;
	  
	  public int ix = 50;
	  public Integer iy = 70;
	  
	  public Inner inner = new Inner();
	}
	
	public static void main(String[] args)
	{
	  Test test = new Test();

	  AutoTree tree = new AutoTree();
	  tree.generate(test);
	  new TreeFrame(tree);
	}
	
	public static class TreeFrame extends JFrame
	{
	  public JTree tree;
	  public JScrollPane pane;
	  
	  public TreeFrame(JTree tree)
	  {
	    this.tree = tree;
	    tree.setEditable(true);
	    pane = new JScrollPane(tree);
	    getContentPane().add(pane);
	    pack();
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	}
}
