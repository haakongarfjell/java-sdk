package no.vipps.model.checkout.initiatesession;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PaymentMerchantInfo {
  @NotNull

  private final String callbackUrl;
  @NotNull

  private final String returnUrl;
  @NotNull

  private final String callbackAuthorizationToken;

  private final String termsAndConditionsUrl;
}
