package com.demo.console.models.data;

public enum ValueAnalysisPlanType {

    plan_traditional, plan_atv, plan_mock;

    public static boolean contains(String s) {
      for (ValueAnalysisPlanType choice : values())
        if (choice.name().equalsIgnoreCase(s))
          return true;
      return false;
    }
    
}
