package no.vipps.model.epayment;

public enum CreatePaymentRequestUserFlow {
  PUSH_MESSAGE("PUSH_MESSAGE"),
  NATIVE_REDIRECT("NATIVE_REDIRECT"),
  WEB_REDIRECT("WEB_REDIRECT"),
  QR("QR");

  private final String value;

  CreatePaymentRequestUserFlow(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
