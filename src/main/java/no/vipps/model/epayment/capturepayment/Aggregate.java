package no.vipps.model.epayment.capturepayment;

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
public class Aggregate {
  private final AuthorizedAmount authorizedAmount;
  private final CancelledAmount cancelledAmount;
  private final CapturedAmount capturedAmount;
  private final RefundedAmount refundedAmount;
}
