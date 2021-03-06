package com.demo.console.messaging;

public class NotificationMessage {

  private String Type;
  private String MessageId;
  private String TopicArn;
  private String Subject;
  private String Message;
  private String Timestamp;
  private String SignatureVersion;
  private String Signature;
  private String SigningCertURL;
  private String UnsubscribeURL;

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    Type = type;
  }

  public String getMessageId() {
    return MessageId;
  }

  public void setMessageId(String messageId) {
    MessageId = messageId;
  }

  public String getTopicArn() {
    return TopicArn;
  }

  public void setTopicArn(String topicArn) {
    TopicArn = topicArn;
  }

  public String getSubject() {
    return Subject;
  }

  public void setSubject(String subject) {
    Subject = subject;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

  public String getTimestamp() {
    return Timestamp;
  }

  public void setTimestamp(String timestamp) {
    Timestamp = timestamp;
  }

  public String getSignatureVersion() {
    return SignatureVersion;
  }

  public void setSignatureVersion(String signatureVersion) {
    SignatureVersion = signatureVersion;
  }

  public String getSignature() {
    return Signature;
  }

  public void setSignature(String signature) {
    Signature = signature;
  }

  public String getSigningCertURL() {
    return SigningCertURL;
  }

  public void setSigningCertURL(String signingCertURL) {
    SigningCertURL = signingCertURL;
  }

  public String getUnsubscribeURL() {
    return UnsubscribeURL;
  }

  public void setUnsubscribeURL(String unsubscribeURL) {
    UnsubscribeURL = unsubscribeURL;
  }

  @Override
  public String toString() {
    return "NotificationMessage [Type=" + Type + ", MessageId=" + MessageId + ", TopicArn="
        + TopicArn + ", Subject=" + Subject + ", Message=" + Message + ", Timestamp=" + Timestamp
        + ", SignatureVersion=" + SignatureVersion + ", Signature=" + Signature
        + ", SigningCertURL=" + SigningCertURL + ", UnsubscribeURL=" + UnsubscribeURL + "]";
  }

}
