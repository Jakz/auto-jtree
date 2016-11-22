package com.jack.autotree.builders;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Stack;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.*;

public class TreeBuilderReflective<T> extends TreeBuilderGeneric<T, T>
{	  
  @SuppressWarnings("unchecked")
  public TreeBuilderReflective(Class<T> clazz)
  {
    super(clazz);
    //super.setClass((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
  }
  
  @SuppressWarnings("unchecked")
  public AutoTreeNode build(T source, AutoTreeContext context)
  {
    Stack<Class<?>> clazzes = new Stack<Class<?>>();
    
    System.out.println("reflective class: "+getClazz());
    
    Class<?> clazz = getClazz();//source.getClass();// getClazz();
    
    while (clazz != null)
    {
      clazzes.push(clazz);
      clazz = clazz.getSuperclass();
    }
    
    InnerNode node = new InnerNode(source);
    
    ValueProxy parentProxy = !context.isEmpty() ? context.peek() : null;
    
    try
    {  
      while (!clazzes.isEmpty())
      {
        Class<?> currentClass = clazzes.pop();
        Field[] fields = currentClass.getDeclaredFields();

        for (Field field : fields)
  	    {
  	      context.push(new FieldProxy(parentProxy, source, field));
  	      
  	      if ((field.getModifiers() & Modifier.STATIC) != 0)
  	        continue;
  	      
  	      if ((field.getModifiers() & Modifier.PUBLIC) == 0)
  	        throw new IllegalAccessException("Field '"+field.getName()+"' of "+currentClass.getName()+" must be public.");
  	      
  	      System.out.println("Building "+field.getName());
  	      //if (field.get(source) != null) 
  	      { 
  	        AutoTreeNode tnode = context.build(field.get(source), field.getType());
            node.add(tnode);
  	      }
  	      
  	      context.pop();
  	    }
      }
    }
    catch (IllegalAccessException e)
    {      
      e.printStackTrace();
    }

    return node;
  }
}