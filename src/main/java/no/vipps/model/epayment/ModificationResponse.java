package no.vipps.model.epayment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import no.vipps.model.VippsResponse;
import no.vipps.model.epayment.refundpayment.Aggregate;
import no.vipps.model.epayment.refundpayment.Amount;

@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class ModificationResponse extends VippsResponse {
  private final Amount amount;
  private final String state;
  private final Aggregate aggregate;
  private final String pspReference;
  private final String reference;
}