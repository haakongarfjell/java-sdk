package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** ResponsePaymentDetailsAggregate. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class ResponsePaymentDetailsAggregate {
  /** */
  private Amount cancelledAmount;
  /** */
  private Amount capturedAmount;
  /** */
  private Amount refundedAmount;
  /** */
  private Amount authorizedAmount;
}
