package com.jack.autotree;

import java.util.Stack;

import com.jack.autotree.nodes.AutoTreeNode;
import com.jack.autotree.proxies.ValueProxy;

public class AutoTreeContext
{
  private final AutoTreeBuilder generator;
  private final Stack<ValueProxy> proxies;
  
  AutoTreeContext(AutoTreeBuilder generator)
  {
    this.generator = generator;
    proxies = new Stack<ValueProxy>();
  }
  
  public <T> AutoTreeNode build(Object object, Class<T> clazz)
  {
    return generator.build(object, clazz);
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
  
  public boolean isEmpty() { return proxies.isEmpty(); }
}