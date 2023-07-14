package no.vipps.model.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder(toBuilder = true)
public class InitCibaRequest {
  private final String scope;

  private final String phoneNumber;

  private final String bindingMessage;

  private final String redirectUri;
}
