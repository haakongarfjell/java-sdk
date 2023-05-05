package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Porterbuddy.
 *
 * <p>Configuration required to enable Porterbuddy logistics options
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class Porterbuddy {
  /** The public key provided to you by Porterbuddy */
  private String publicToken;
  /** The API key provided to you by Porterbuddy */
  private String apiKey;
  /** */
  private PorterbuddyOrigin origin;
}
