package com.jack.autotree.types;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TypeMapper<K extends AutoType, M>
{
  private final Map<K, M> defaultMapping;
  private final Map<K, M> mapping;
  private final Map<K, M> defaultInheritanceMapping;
  private final Map<K, M> inheritanceMapping;
  private final Map<K, M> inheritanceCache;
  private M defaultElement;
  
  public TypeMapper()
  {
    defaultMapping = new HashMap<K, M>();
    mapping = new HashMap<K, M>();
    defaultInheritanceMapping = new HashMap<K, M>();
    inheritanceMapping = new HashMap<K, M>();
    inheritanceCache = new HashMap<K, M>();
  }
  
  public void flushCache()
  {
    inheritanceCache.clear();
  }
  
  public void setDefault(M defaultElement)
  {
    this.defaultElement = defaultElement;
  }
  
  public void registerDefault(K type, M element)
  {
    defaultMapping.put(type, element);
  }
  
  public void register(K type, M element)
  {
    mapping.put(type, element);
  }
  
  public void registerHierarchy(K type, M element)
  {
    inheritanceMapping.put(type, element);
  }
  
  public void registerHierarchyDefault(K type, M element)
  {
    defaultInheritanceMapping.put(type, element);
  }
  
  public M get(K type)
  {
    M value = mapping.getOrDefault(type, inheritanceCache.getOrDefault(type, defaultMapping.get(type)));
    
    if (value != null)
      return value;
    else
    {
      Optional<Map.Entry<K, M>> ibuilder = inheritanceMapping.entrySet().stream().
          filter( e -> type.isSubtypeOf(e.getKey())).findFirst();
      
      if (!ibuilder.isPresent())
        ibuilder = defaultInheritanceMapping.entrySet().stream().
          filter( e -> type.isSubtypeOf(e.getKey())).findFirst();
      
      if (ibuilder.isPresent())
      {
        inheritanceCache.put(type, ibuilder.get().getValue());
        return ibuilder.get().getValue();
      }
      
      return defaultElement;
    }
  }
}
