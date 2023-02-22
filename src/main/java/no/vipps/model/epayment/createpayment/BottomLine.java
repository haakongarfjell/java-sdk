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
public class BottomLine {
  public final String currency;
  public final Integer tipAmount;
  public final Integer giftCardAmount;
  public final String terminalId;
  public final Integer totalAmount;
  public final Integer totalTax;
  public final Integer totalDiscount;
  public final Integer shippingAmount;
  public final ShippingInfo shippingInfo;
}
