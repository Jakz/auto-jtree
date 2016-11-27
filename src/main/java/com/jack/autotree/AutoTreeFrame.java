package com.jack.autotree;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class AutoTreeFrame extends JFrame
{
  public AutoTree tree;
  public JScrollPane pane;
  public JButton btAdd = new JButton("+");
  
  public AutoTreeFrame(AutoTree tree)
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