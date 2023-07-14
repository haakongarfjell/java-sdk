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
public class InitCibaBody {
  @JsonProperty("scope")
  private final String scope;

  @JsonProperty("login_hint")
  private final String loginHint;

  private final String state;

  @JsonProperty("binding_message")
  private final String bindingMessage;

  @JsonProperty("client_id")
  private String clientId;

  @JsonProperty("client_secret")
  private String clientSecret;

  @JsonProperty("requested_flow")
  private String requestedFlow;

  @JsonProperty("redirect_uri")
  private String redirectUri;
}
