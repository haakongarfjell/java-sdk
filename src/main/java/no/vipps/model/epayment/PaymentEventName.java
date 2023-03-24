package no.vipps.model.epayment;

public enum PaymentEventName {
  CREATED("CREATED"),
  ABORTED("ABORTED"),
  EXPIRED("EXPIRED"),
  CANCELLED("CANCELLED"),
  CAPTURED("CAPTURED"),
  REFUNDED("REFUNDED"),
  AUTHORIZED("AUTHORIZED"),
  TERMINATED("TERMINATED");

  private String value;

  PaymentEventName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
