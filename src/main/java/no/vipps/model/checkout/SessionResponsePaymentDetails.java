package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** SessionResponsePaymentDetails. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class SessionResponsePaymentDetails {
  /** */
  private ResponsePaymentDetailsAmount amount;
  /** */
  private PaymentState state;
  /** */
  private ResponsePaymentDetailsAggregate aggregate;
}
