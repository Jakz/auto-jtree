package com.jack.autotree.builders;

import java.lang.reflect.Field;

import com.jack.autotree.AutoTreeContext;
import com.jack.autotree.nodes.InnerNode;
import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.FieldProxy;

public class TreeBuilderReflective<T> implements TreeBuilder<T>
{	  
  public AutoTreeNode build(T source, AutoTreeContext context)
  {
    Field[] fields = source.getClass().getDeclaredFields();
    
    InnerNode node = new InnerNode(source);
    
    try
    {  
	    for (Field field : fields)
	    {
	      context.push(new FieldProxy(source, field));
	      
	      if (field.get(source) != null) 
	      {
	        AutoTreeNode tnode = context.build(field.get(source));
          node.add(tnode);
	      }
	      
	      context.pop();
	    }
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }

    return node;
  }
}