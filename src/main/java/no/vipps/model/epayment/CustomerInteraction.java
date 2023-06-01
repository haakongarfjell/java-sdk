package no.vipps.model.epayment;

public enum CustomerInteraction {
  CUSTOMER_PRESENT("CUSTOMER_PRESENT"),
  CUSTOMER_NOT_PRESENT("CUSTOMER_NOT_PRESENT");

  private final String value;

  CustomerInteraction(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
