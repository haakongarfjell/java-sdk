package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * TransactionAggregate.
 *
 * <p>Defines the details of the transaction
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class TransactionAggregate {
  /** */
  private Amount cancelledAmount;
  /** */
  private Amount capturedAmount;
  /** */
  private Amount refundedAmount;
  /** */
  private Amount authorizedAmount;
}
