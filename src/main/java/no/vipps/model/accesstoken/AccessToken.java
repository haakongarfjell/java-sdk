package no.vipps.model.accesstoken;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonProperty("token_type")
  private final String tokenType;

  @JsonProperty("expires_in")
  private final String expiresIn;

  @JsonProperty("ext_expires_in")
  private final String extExpiresIn;

  @JsonProperty("expires_on")
  private final String expiresOn;

  @JsonProperty("not_before")
  private final String notBefore;

  @JsonProperty("resource")
  private final String resource;

  @JsonProperty("access_token")
  private final String token;
}
