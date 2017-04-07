package com.demo.console.models.data;

public enum ValidEnum {
  AND, OR, NOT;

  public static boolean contains(String s) {
    for (ValidEnum choice : values())
      if (choice.name().equalsIgnoreCase(s))
        return true;
    return false;
  }

};