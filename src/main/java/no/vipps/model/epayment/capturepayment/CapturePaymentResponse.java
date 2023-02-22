package no.vipps.model.epayment.capturepayment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import no.vipps.model.VippsResponse;

@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class CapturePaymentResponse extends VippsResponse {
  private final Amount amount;
  private final String state;
  private final Aggregate aggregate;
  private final String pspReference;
  private final String reference;
}
