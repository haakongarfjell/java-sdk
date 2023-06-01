package no.vipps.model.epayment;

public enum PaymentAction {
  CREATE("CREATE"),
  ABORT("ABORT"),
  EXPIRE("EXPIRE"),
  CANCEL("CANCEL"),
  CAPTURE("CAPTURE"),
  REFUND("REFUND"),
  AUTHORISE("AUTHORISE"),
  TERMINATE("TERMINATE");

  private final String value;

  PaymentAction(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
