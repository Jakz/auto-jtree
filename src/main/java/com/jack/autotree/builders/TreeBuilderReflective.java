package com.jack.autotree.builders;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Stack;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.annotations.Hidden;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.InnerObjectNode;
import com.jack.autotree.nodes.NullNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.*;
import com.jack.autotree.types.AutoType;
import com.jack.autotree.types.GenericType;
import com.jack.autotree.types.RawType;

public class TreeBuilderReflective extends TreeBuilderGeneric
{	  
  public TreeBuilderReflective()
  {
  }
  
  public AutoTreeNode build(Object source, final AutoType type, AutoTreeContext context)
  {
    Stack<Class<?>> clazzes = new Stack<Class<?>>();
    
    System.out.println("reflective class: "+type);
    
    Class<?> clazz = type.get();
    
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
        InnerNode node = new InnerObjectNode(context.generator(), parentProxy, source);
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
    	        continue;
    	        //throw new IllegalAccessException("Field '"+field.getName()+"' of "+currentClass.getName()+" must be public.");
    	      
    	      if (field.getAnnotation(Hidden.class) != null)
    	        continue;
    	      
            AutoType ftype = null;
            
    	      if (field.getGenericType() instanceof ParameterizedType)
    	      {
    	        ParameterizedType pfield = (ParameterizedType)field.getGenericType();
    	        ftype = new GenericType((Class<?>)pfield.getRawType(), pfield.getActualTypeArguments());
    	      }
    	      else
    	      {
    	        ftype = new RawType(field.getType());
    	      }

    	      AutoTreeNode tnode = context.build(field.get(source), ftype);
    	      node.add(tnode);
    	      
    	      context.pop();
    	    }
        }
        
        return node;
      }
      else   
      {
        //TODO: verify if it's still needed
        return new NullNode(context.generator(), parentProxy, parentProxy.mnemonic(), type);
      }
    }
    catch (IllegalAccessException e)
    {      
      e.printStackTrace();
      return null;
    }
  }
}