package no.vipps.model.login;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder(toBuilder = true)
public class StartLoginUriRequest {
  private String scope;

  private String redirectUri;

  private AuthenticationMethod authenticationMethod;
}
