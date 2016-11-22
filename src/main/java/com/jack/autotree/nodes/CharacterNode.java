package com.jack.autotree.nodes;

import com.jack.autotree.proxies.ValueProxy;

public class CharacterNode extends PrimitiveNode<Character>
{
  public CharacterNode(ValueProxy proxy)
  {
    super(proxy);
  }
  
  public String mnemonic()
  {
    return proxy.mnemonic()+": "+Character.valueOf(getValue());
  }
}