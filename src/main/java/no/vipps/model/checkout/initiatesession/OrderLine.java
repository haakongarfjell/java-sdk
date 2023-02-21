package no.vipps.model.checkout.initiatesession;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder

public class OrderLine {

  private final String name;

  private final int quantity;

  private final int unitPrice;

  private final String taxRate;
}
