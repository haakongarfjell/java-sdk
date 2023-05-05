package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * UserInfo.
 *
 * <p>Data from the UserInfo endpoint. Will only be present if UserInfo flow is used.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class UserInfo {
  /** The openid sub that uniquely identifies a Vipps user. */
  private String sub;
  /** Example: "user@example.com" */
  private String email;
}
