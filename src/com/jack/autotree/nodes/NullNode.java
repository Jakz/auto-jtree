package com.jack.autotree.nodes;

public class NullNode extends LeafNode
{
  private final Class<?> clazz;
  private final String caption;
  
  public NullNode(String caption, Class<?> clazz)
  {
    this.caption = caption;
    this.clazz = clazz;
  }
  
  @Override
  public String mnemonic()
  {
    return caption+": null";
  }

}
