package com.jack.autotree.proxies;

public interface ValueProxy
{
  <T> T get();
  <T> void set(T value);
  String mnemonic();
  boolean isEditable();
}