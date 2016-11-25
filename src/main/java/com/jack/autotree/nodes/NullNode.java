package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;

public class NullNode extends LeafNode
{
  private final Class<?> clazz;
  private final String caption;
  
  public NullNode(AutoTreeBuilder builder, ValueProxy proxy, String caption, Class<?> clazz)
  {
    super(builder, proxy);
    this.caption = caption;
    this.clazz = clazz;
  }
  
  @Override
  public String mnemonic()
  {
    return caption+": null";
  }

}
