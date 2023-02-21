package no.vipps.model.checkout.initiatesession;

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
public class CheckoutConfig {

  private final CustomerInteraction customerInteraction;

  private final Elements elements;

  private final Countries countries;

  private final UserFlow userFlow;

  private final Boolean requireUserInfo;
}
