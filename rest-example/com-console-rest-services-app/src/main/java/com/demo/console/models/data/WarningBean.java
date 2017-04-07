package com.demo.console.models.data;

import java.util.Set;

public class WarningBean {
  Set<String> missingField;

  public Set<String> getMissingField() {
    return missingField;
  }

  public void setMissingField(Set<String> missingField) {
    this.missingField = missingField;
  }
}
