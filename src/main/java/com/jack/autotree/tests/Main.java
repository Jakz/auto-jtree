package com.jack.autotree.tests;

import java.awt.BorderLayout;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import javax.swing.*;

import com.jack.autotree.AutoTree;
import com.jack.autotree.tests.MapSurface.Deco;

public class Main
{
  public static class Point
  {
    public float x;
    public float y;
    
    Point(float x, float y)
    {
      this.x = x;
      this.y = y;
    }
  }
  
  public static class Point3D extends Point
  {
    public float z;
    
    Point3D(float x, float y, float z)
    {
      super(x,y);
      this.z = z;
    }
  }
  
	public static class Triangle
	{
	  public Point3D p1, p2, p3;
	  public Triangle()
	  {
	    p1 = new Point3D(10,20,30);
	    p2 = new Point3D(20,20,30);
	    p3 = new Point3D(30,20,30);
	  }
	}
	
	public static class Shape
	{
	  public List<Point> points;
	  
	  Shape()
	  {
	    points = new ArrayList<Point>();
	    points.add(new Point3D(10,20,30));
	    points.add(new Point(10,20));
	  }
	}
  
  public static class Test
	{
	  //public Shape shape = new Shape();
	  public Triangle triangle = new Triangle();
	  public Triangle[] data = new Triangle[0];
	}
  
  static class Erasure<T> {
    public Class<?> type() { return getClass(); }
    
  }
	
	public static void main(String[] args)
	{
	   try {
	      for (UIManager.LookAndFeelInfo laf: UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(laf.getName())) {
	          UIManager.setLookAndFeel(laf.getClassName());
	        }
	      }
	    } catch (ClassNotFoundException | InstantiationException
	               | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	      ex.printStackTrace();
	    }
	  
	  /*GenericType foo = new GenericType(new Erasure<HashMap<Integer, String>>() {}.type());
    System.out.println(foo);
    
    if (true)
      return;*/

	  Test test = new Test();
	  //MapSurface test = new MapSurface();
	  
	  MapSurface.Deco[] decos = new MapSurface.Deco[3];
	  decos[0] = new MapSurface.Deco();
	  decos[0].block = new Block();
	  decos[0].replacement = new Block[2];
	  decos[0].block.name = "AAAA";
	  
	  AutoTree tree = new AutoTree();
	  tree.registerRenderer(Float.class, new TestNodeRenderer());
	  tree.registerRenderer(Float.TYPE, new TestNodeRenderer());
	  tree.generate(test, Test.class);
	  new TreeFrame(tree);
	}
	
	public static class TreeFrame extends JFrame
	{
	  public AutoTree tree;
	  public JScrollPane pane;
	  public JButton btAdd = new JButton("+");
	  
	  public TreeFrame(AutoTree tree)
	  {	    
	    btAdd.addActionListener(e -> tree.extendElement(tree.getSelectionPath()));
	    
	    this.tree = tree;
	    tree.setEditable(true);
	    pane = new JScrollPane(tree);
	    pane.setPreferredSize(new java.awt.Dimension(800,800));
	    setLayout(new BorderLayout());
	    add(pane, BorderLayout.CENTER);
	    add(btAdd, BorderLayout.SOUTH);
	    pack();
	    this.setLocationRelativeTo(null);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	}
}
