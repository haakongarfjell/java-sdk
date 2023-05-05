package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** ResponsePaymentDetailsAmount. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class ResponsePaymentDetailsAmount {
  /** Must be in Minor Units. The smallest unit of a currency. Example 100 NOK &#x3D; 10000. */
  private Integer value;
  /** The currency identifier according to ISO 4217. Example: "NOK" */
  private String currency;
}
