package no.vipps.model.epayment.getpayment;

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
public class GetPaymentResponse extends VippsResponse {
  private final Aggregate aggregate;
  private final Amount amount;
  private final String state;
  private final PaymentMethod paymentMethod;
  private final Profile profile;
  private final String pspReference;
  private final String redirectUrl;
  private final String reference;
}
