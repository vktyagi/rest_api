package com.demo.console.wrapper.auth.wso2;

public class WSO2Config {

  private String authURL;
  private String username;
  private String password;
  private String grantType;
  private String clientId;
  private String clientSecret;

  public WSO2Config() {

  }

  public WSO2Config(String authURL, String username, String password, String grantType,
      String clientId, String clientSecret) {
    this.authURL = authURL;
    this.username = username;
    this.password = password;
    this.grantType = grantType;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public String getAuthURL() {
    return authURL;
  }

  public void setAuthURL(String authURL) {
    this.authURL = authURL;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

}
