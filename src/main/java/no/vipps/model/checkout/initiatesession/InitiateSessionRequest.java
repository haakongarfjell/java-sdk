package no.vipps.model.checkout.initiatesession;

import jakarta.validation.constraints.NotNull;
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
public class InitiateSessionRequest extends VippsRequest {
  @NotNull
  private final PaymentMerchantInfo merchantInfo;
  @NotNull

  private final PaymentTransaction transaction;

  private final Logistics logistics;

  private final PrefillCustomer prefillCustomer;

  private final CheckoutConfig configuration;
}
