package no.vipps.model.accesstoken;

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
public class AccessToken {

  private final String tokenType;

  private final String expiresIn;

  private final String extExpiresIn;

  private final String expiresOn;

  private final String notBefore;

  private final String resource;

  private final String token;
}
