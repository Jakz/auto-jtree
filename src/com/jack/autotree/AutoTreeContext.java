package com.jack.autotree;

import java.util.Stack;

import com.jack.autotree.nodes.TreeNode;
import com.jack.autotree.proxies.ValueProxy;

public class AutoTreeContext
{
  private final AutoTree generator;
  private final Stack<ValueProxy> proxies;
  
  AutoTreeContext(AutoTree generator)
  {
    this.generator = generator;
    proxies = new Stack<ValueProxy>();
  }
  
  public <T> TreeNode build(T object)
  {
    return generator.build(object);
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
}