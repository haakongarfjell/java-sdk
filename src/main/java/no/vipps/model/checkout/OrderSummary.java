package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/** OrderSummary. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class OrderSummary {
  /** The order lines contain descriptions of each item present in the order. */
  private List<OrderLine> orderLines;
  /** */
  private OrderSummaryOrderBottomLine orderBottomLine;
}
