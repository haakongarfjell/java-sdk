package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * CustomConsent.
 *
 * <p>If used, displays a checkbox that can be used to ask for extra consent.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class CustomConsent {
  /**
   * Text displayed next to the checkbox. This text can contain up to one link in markdown format
   * like this: [linkText](https://example.com)
   */
  private String text;
  /** Whether box has to be checked to complete the checkout. */
  private Boolean required;
}
