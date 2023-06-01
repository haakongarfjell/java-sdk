package no.vipps.model.checkout;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * InitiateSessionResponse.
 *
 * <p>Response from initiating a session.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class InitiateSessionResponse {
  /**
   * The token to be provided to Checkout. Example:
   * "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJzZXNzaW9uSWQiOiJUdHF1Y3I5ZDdKRHZ6clhYWTU1WUZRIiwic2Vzc2lvblBvbGxpbmdVUkwiOiJodHRwOi8vbG9jYWxob3N0OjUwMDAvY2hlY2tvdXQvc2Vzc2lvbi9UdHF1Y3I5ZDdKRHZ6clhYWTU1WUZRIn0.ln7VzZkNvUGu0HhyA_a8IbXQN35WhDBmCYC9IvyYL-I"
   */
  private String token;
  /** The URL of the checkout frontend. Example: "https://vippscheckout.vipps.no/v1/". */
  private String checkoutFrontendUrl;
  /**
   * The URL to poll for session information. Example:
   * "https://api.vipps.no/checkout/v1/session/31gf1g413121".
   */
  private String pollingUrl;

  @Singular @JsonAnySetter @JsonAnyGetter private Map<String, Object> additionalProperties;
}
