package no.vipps.model.checkout.initiatesession;

import jakarta.validation.constraints.NotNull;
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
public class PaymentTransaction {
  @NotNull

  private final Amount amount;
  @NotNull

  private final String reference;
  @NotNull

  private final String paymentDescription;

  private final OrderSummary orderSummary;
}
