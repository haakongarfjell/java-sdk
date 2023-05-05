package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Instabox.
 *
 * <p>Configuration required to enable Instabox logistics options
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class Instabox {
  /** The client id provided to you by Instabox */
  private String clientId;
  /** The client secret provided to you by Instabox */
  private String clientSecret;
}
