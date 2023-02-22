package no.vipps.model.epayment.capturepayment;

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
public class CapturePaymentRequest extends VippsRequest {
  private ModificationAmount modificationAmount;
}
