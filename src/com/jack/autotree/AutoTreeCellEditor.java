package com.jack.autotree;

import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.jack.autotree.nodes.*;

public class AutoTreeCellEditor extends DefaultTreeCellEditor
{
  
  AutoTreeCellEditor(JTree tree)
  {
    super(tree, new DefaultTreeCellRenderer(), new InnerEditor(tree));
    
    /*this.addCellEditorListener(
        new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("editingCanceled");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("editingStopped: apply additional action");
            }
        });*/
  }

  private static class InnerEditor extends DefaultTreeCellEditor
  {
    JTextField field;
    JComboBox booleanBox;
    Class<?> clazz;
    boolean ignoreValue;
    
    AbstractAction doneAction = new AbstractAction(){
      public void actionPerformed(ActionEvent e) {
        if (isNewValueValid())
        {
          ignoreValue = false;
          InnerEditor.this.stopCellEditing();
        }
        else
        {
          ignoreValue = true;
          InnerEditor.this.cancelCellEditing();
        }
    }};

    InnerEditor(JTree tree)
    {
      super(tree, new DefaultTreeCellRenderer());
      field = new JTextField(15);
      field.addActionListener(doneAction);
      
      booleanBox = new JComboBox(new String[]{"true", "false"});
      booleanBox.addActionListener(doneAction);
      
      ignoreValue = false;
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row)
    {
      clazz = value.getClass();
      
      if (value instanceof PrimitiveNode)
      {
        if (value instanceof BooleanNode)
        {
          booleanBox.setSelectedItem(((PrimitiveNode<?>)value).getValue().toString());
          return booleanBox;
        }
        else
        {
          field.setText(((PrimitiveNode<?>)value).getValue().toString());
          return field;
        }
      }

      return null;
    }
    
    private boolean isNewValueValid()
    {
      try
      {
        if (clazz == FloatNode.class)
          Float.valueOf(field.getText());
        else if (clazz == DoubleNode.class)
          Double.valueOf(field.getText());
        else if (clazz == IntegerNode.class)
          Integer.valueOf(field.getText());
        else if (clazz == ByteNode.class)
          Byte.valueOf(field.getText());
        else if (clazz == ShortNode.class)
          Short.valueOf(field.getText());
        else if (clazz == CharacterNode.class)
          return field.getText().length() == 1;
        else if (clazz == LongNode.class)
          Long.valueOf(field.getText());
        
      }
      catch (NumberFormatException e)
      {
        return false;
      }
       
      return true;
    }
    
    @Override
    public Object getCellEditorValue()
    {
      if (ignoreValue) return null;
      
      if (clazz == FloatNode.class)
        return Float.valueOf(field.getText());
      else if (clazz == DoubleNode.class)
        return Double.valueOf(field.getText());
      else if (clazz == IntegerNode.class)
        return Integer.valueOf(field.getText());
      else if (clazz == ByteNode.class)
        return Byte.valueOf(field.getText());
      else if (clazz == ShortNode.class)
        return Short.valueOf(field.getText());
      else if (clazz == CharacterNode.class)
        return field.getText().charAt(0);
      else if (clazz == LongNode.class)
        return Long.valueOf(field.getText());
      else if (clazz == BooleanNode.class)
        return Boolean.valueOf((String)booleanBox.getSelectedItem());
      else
        return null;
    }
  }
}
