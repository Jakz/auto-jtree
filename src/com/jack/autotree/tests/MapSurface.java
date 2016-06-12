package com.jack.autotree.tests;

import java.util.*;

public class MapSurface
{
  public static class Deco
  {
    public String block;
    public Block[] replacement;
    public float chance;
  }

  public static class Dress
  {
    public String[] filters;
    public String flavour;
    public DressSpec[] specs;
  }

  public static class DressSpec
  {
    public float chance;
    public List<DressSpecSingle> data;
  }

  public static class PropSpecItem
  {
    public Block block;
    public int min;
    public int max;
    PropSpecItem() { min = 1; max = 1; }
  }

  public static class DressSpecSingle
  {
    public static enum Type
    {
      single,
      multi,
      tree,
      flower,
      treasure,
      bridge,
      floating
    };

    public static enum Direction
    {
      floor,
      ceil,
      vertical,
      sides,
      left,
      right,
      all
    }

    public Type type;
    public int treeIdent;
    public boolean flippable;
    public boolean sameVariant;
    public Direction direction;
    public List<PropSpecItem> data;
  }

  public Deco[] decoSurface;
  public Dress[] dress;
  
  MapSurface()
  {
    decoSurface = new Deco[3];
    dress = new Dress[3];
    
    for (int i = 0; i < 3; ++i)
    {
      decoSurface[i] = new Deco();
      dress[i] = new Dress();
    }
  }
}
