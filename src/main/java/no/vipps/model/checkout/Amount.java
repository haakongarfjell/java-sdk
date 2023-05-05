package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Amount.
 *
 * <p>Amounts are specified in minor units. For Norwegian kroner (NOK) that means 1 kr &#x3D; 100
 * øre. Example: 499 kr &#x3D; 49900 øre.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class Amount {
  /** Must be in Minor Units. The smallest unit of a currency. Example 100 NOK &#x3D; 10000. */
  private Integer value;
  /** The currency identifier according to ISO 4217. Example: "NOK" */
  private String currency;
}
