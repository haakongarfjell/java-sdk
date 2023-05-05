package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** OrderUnitInfo. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class OrderUnitInfo {
  /**
   * Total price per unit, including tax and excluding discount. Must be in Minor Units. The
   * smallest unit of a currency. Example 100 NOK &#x3D; 10000.
   */
  private Long unitPrice;
  /** Quantity given as a integer or fraction (only for cosmetics). */
  private String quantity;
  /** Available units for quantity. Will default to PCS if not set. */
  private QuantityUnit quantityUnit;
}
