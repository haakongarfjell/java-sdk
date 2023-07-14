package no.vipps.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class TokenRequest {

  @JsonProperty("grant_type")
  private String grantType;

  @JsonProperty("code")
  private final String code;

  @JsonProperty("redirect_uri")
  private final String redirectUri;

  @JsonProperty("client_id")
  private String clientId;

  @JsonProperty("client_secret")
  private String clientSecret;

  @JsonProperty("code_verifier")
  private final String codeVerifier;
}
