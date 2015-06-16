package com.jack.autotree.tests;

import javax.swing.tree.*;
import javax.swing.*;

import com.jack.autotree.AutoTree;

public class Main
{
	public static class Test
	{
	  
	  public float fx = 20.0f;
	  public Float fy = 30.0f;

	  public boolean bx = false;
	  public Boolean by = Boolean.TRUE;
	  
	  public int ix = 50;
	  public Integer iy = 70;
	}
	
	public static void main(String[] args)
	{
	  Test test = new Test();

	  AutoTree generator = new AutoTree();
	  new TreeFrame(generator.generate(test));
	}
	
	public static class TreeFrame extends JFrame
	{
	  public JTree tree;
	  public JScrollPane pane;
	  
	  public TreeFrame(TreeModel model)
	  {
	    tree = new JTree(model);
	    pane = new JScrollPane(tree);
	    getContentPane().add(pane);
	    pack();
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	}
}
