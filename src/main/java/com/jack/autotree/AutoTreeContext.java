package com.jack.autotree;

import java.util.Stack;

import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.ValueProxy;
import com.jack.autotree.types.AutoType;

public class AutoTreeContext
{
  private final AutoTreeBuilder generator;
  private final Stack<ValueProxy> proxies;
  
  AutoTreeContext(AutoTreeBuilder generator)
  {
    this.generator = generator;
    proxies = new Stack<ValueProxy>();
  }
  
  public Object instatiate(Class<?> clazz)
  {
    return generator.instantiate(clazz);
  }
  
  public <T> AutoTreeNode build(Object object, AutoType type)
  {
    return generator.build(object, type);
  }
  
  public void push(ValueProxy proxy)
  {
    proxies.push(proxy);
  }
  
  public void pop()
  {
    proxies.pop();	    
  }
  
  public ValueProxy peek()
  {
    return proxies.peek();
  }
  
  public AutoTreeBuilder generator()
  {
    return generator;
  }
  
  public boolean isEmpty() { return proxies.isEmpty(); }
}