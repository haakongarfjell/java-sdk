package no.vipps.model.epayment.createpayment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder(toBuilder = true)
public class ShippingInfo {
  public final Integer amount;
  public final Integer amountExcludingTax;
  public final Integer taxAmount;
  public final Integer taxPercentage;
}
