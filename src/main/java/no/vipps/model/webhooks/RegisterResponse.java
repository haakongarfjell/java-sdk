package no.vipps.model.webhooks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class RegisterResponse {

  @JsonProperty("id")
  private final String id;

  @JsonProperty("secret")
  private final String secret;
}
