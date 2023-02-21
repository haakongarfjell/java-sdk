package no.vipps.model.checkout.initiatesession;

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
public class InitiateSessionResponse extends VippsResponse {
  private final String token;

  private final String checkoutFrontendUrl;

  private final String pollingUrl;
}
