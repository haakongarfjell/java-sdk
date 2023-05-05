package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** OrderBottomLine. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class OrderBottomLine {
  /** The currency identifier according to ISO 4217. Example: "NOK". */
  private String currency;
  /**
   * Tip amount for the order. Must be in Minor Units. The smallest unit of a currency. Example 100
   * NOK &#x3D; 10000.
   */
  private Long tipAmount;
  /**
   * Amount paid by gift card or coupon. Must be in Minor Units. The smallest unit of a currency.
   * Example 100 NOK &#x3D; 10000.
   */
  private Long giftCardAmount;
  /** Identifier of the terminal / point of sale. */
  private String terminalId;
}
