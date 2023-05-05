package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** SessionResponseUserInfo. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class SessionResponseUserInfo {
  /** The openid sub that uniquely identifies a Vipps user. */
  private String sub;
  /** Example: "user@example.com" */
  private String email;
}
