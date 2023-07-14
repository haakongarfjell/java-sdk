package no.vipps.model.login;

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
public class OauthTokenResponse {

  @JsonProperty("access_token")
  private final String accessToken;

  @JsonProperty("expires_in")
  private final long expiresIn;

  @JsonProperty("id_token")
  private final String idToken;

  @JsonProperty("refresh_token")
  private final String refreshToken;

  @JsonProperty("scope")
  private final String scope;

  @JsonProperty("token_type")
  private final String tokenType;
}
