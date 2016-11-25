package com.jack.autotree.builders;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Stack;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.NullNode;
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
    
    try
    {             
      while (clazz != null)
      {
        clazzes.push(clazz);
        clazz = clazz.getSuperclass();
      }
          
      ValueProxy parentProxy = context.peek();

      if (source != null)
      {
        InnerNode node = new InnerNode(context.generator(), parentProxy, source);
        while (!clazzes.isEmpty())
        {
          Class<?> currentClass = clazzes.pop();
          
          if ((currentClass.getModifiers() & Modifier.PUBLIC) == 0)
            throw new IllegalAccessException("Class '"+currentClass.getCanonicalName()+"' must be public to be accessed from tree builder");
          if (currentClass.isMemberClass() && (currentClass.getModifiers() & Modifier.STATIC) == 0)
            throw new IllegalAccessException("Class '"+currentClass.getCanonicalName()+"' must be static to be accessed from tree builder");
  
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
        
        return node;
      }
      else   
      {
        return new NullNode(context.generator(), parentProxy, parentProxy.mnemonic(), getClazz());
      }
    }
    catch (IllegalAccessException e)
    {      
      e.printStackTrace();
      return null;
    }
  }
}