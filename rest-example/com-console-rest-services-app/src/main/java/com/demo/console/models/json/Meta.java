package com.demo.console.models.json;

public class Meta {
  private String delimiter;

  private String cursor;

  private String aborted;

  private String linebreak;

  private String truncated;

  private String[] fields;

  public String getDelimiter() {
    return delimiter;
  }

  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  public String getCursor() {
    return cursor;
  }

  public void setCursor(String cursor) {
    this.cursor = cursor;
  }

  public String getAborted() {
    return aborted;
  }

  public void setAborted(String aborted) {
    this.aborted = aborted;
  }

  public String getLinebreak() {
    return linebreak;
  }

  public void setLinebreak(String linebreak) {
    this.linebreak = linebreak;
  }

  public String getTruncated() {
    return truncated;
  }

  public void setTruncated(String truncated) {
    this.truncated = truncated;
  }

  public String[] getFields() {
    return fields;
  }

  public void setFields(String[] fields) {
    this.fields = fields;
  }

  @Override
  public String toString() {
    return "ClassPojo [delimiter = " + delimiter + ", cursor = " + cursor + ", aborted = "
        + aborted + ", linebreak = " + linebreak + ", truncated = " + truncated + ", fields = "
        + fields + "]";
  }
}
