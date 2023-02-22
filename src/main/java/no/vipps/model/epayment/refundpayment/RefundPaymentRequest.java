package no.vipps.model.epayment.refundpayment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import no.vipps.model.VippsRequest;

@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class RefundPaymentRequest extends VippsRequest {
  public ModificationAmount modificationAmount;
}
