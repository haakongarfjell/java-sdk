package no.vipps.model.checkout;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * PaymentTransactionOrderSummary.
 *
 * <p>Contain descriptions of each item present in the order, and an order bottom line for
 * information regarding the order as a whole.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PaymentTransactionOrderSummary {
  /** The order lines contain descriptions of each item present in the order. */
  private List<OrderLine> orderLines;
  /** */
  private OrderSummaryOrderBottomLine orderBottomLine;
}
