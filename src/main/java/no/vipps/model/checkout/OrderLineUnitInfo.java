package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * OrderLineUnitInfo.
 *
 * <p>If no quantity info is provided the order line will default to 1 pcs.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class OrderLineUnitInfo {
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
