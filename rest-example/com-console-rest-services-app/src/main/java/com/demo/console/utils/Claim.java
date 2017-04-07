package com.demo.console.utils;

public class Claim {
  private String iss;

  private long exp;

  private String subscriber;

  private String email;

  private String role;

  private String endUser;

  private String username;

  public Claim() {
  }

  public Claim(String iss, long exp, String subscriber, String email, String role, String endUser) {
    this.iss = iss;
    this.exp = exp;
    this.subscriber = subscriber;
    this.email = email;
    this.role = role;
    this.setEndUser(endUser);
    if (endUser.contains("@")) {
      this.username = endUser.substring(0, endUser.lastIndexOf("@"));
    } else {
      this.username = endUser;
    }
  }

  /**
   * @return the iss
   */
  public String getIss() {
    return iss;
  }

  /**
   * @param iss
   *          the iss to set
   */
  public void setIss(String iss) {
    this.iss = iss;
  }

  /**
   * @return the exp
   */
  public long getExp() {
    return exp;
  }

  /**
   * @param exp
   *          the exp to set
   */
  public void setExp(long exp) {
    this.exp = exp;
  }

  /**
   * @return the subscriber
   */
  public String getSubscriber() {
    return subscriber;
  }

  /**
   * @param subscriber
   *          the subscriber to set
   */
  public void setSubscriber(String subscriber) {
    this.subscriber = subscriber;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /**
   * @param role
   *          the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }

  public String getEndUser() {
    return endUser;
  }

  public void setEndUser(String endUser) {
    this.endUser = endUser;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Claim [iss=" + iss + ", exp=" + exp + ", subscriber=" + subscriber + ", email=" + email
        + ", role=" + role + "]";
  }

}
