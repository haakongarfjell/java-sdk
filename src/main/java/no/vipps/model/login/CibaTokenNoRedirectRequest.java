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
public class CibaTokenNoRedirectRequest {
  @JsonProperty("auth_req_id")
  private final String authReqId;

  @JsonProperty("grant_type")
  private final String grantType;

  @JsonProperty("client_id")
  private String clientId;

  @JsonProperty("client_secret")
  private String clientSecret;
}
