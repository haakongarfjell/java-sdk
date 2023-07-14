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
public class InitCibaResponse {
  @JsonProperty("auth_req_id")
  private final String authReqId;

  @JsonProperty("expires_in")
  private final long expiresIn;

  @JsonProperty("interval")
  private final long interval;
}
