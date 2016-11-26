package com.jack.autotree.builders;

abstract class TreeBuilderGeneric<T,K> implements TreeBuilder<T>
{
  private Class<K> clazz;
  
  protected Class<K> getClazz()
  {
    return clazz;
  }

  protected TreeBuilderGeneric()
  {
  }
  
  protected TreeBuilderGeneric(Class<K> clazz)
  {
    this.clazz = clazz;
  }
}
