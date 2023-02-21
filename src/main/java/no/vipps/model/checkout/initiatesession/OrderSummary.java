package no.vipps.model.checkout.initiatesession;

import java.util.List;
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

public class OrderSummary {

  private final List<OrderLine> orderLines;

  private final String orderBottomLine;
}
