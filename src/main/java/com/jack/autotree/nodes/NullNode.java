package com.jack.autotree.nodes;

import com.jack.autotree.AutoTreeBuilder;
import com.jack.autotree.proxies.ValueProxy;
import com.jack.autotree.types.AutoType;

public class NullNode extends LeafNode
{
  private final AutoType type;
  private final String caption;
  
  public NullNode(AutoTreeBuilder builder, ValueProxy proxy, String caption, AutoType type)
  {
    super(builder, proxy);
    this.caption = caption;
    this.type = type;
  }
  
  @Override
  public String mnemonic()
  {
    return caption+": null";
  }

}
