package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * InitiateSessionRequestPrefillCustomer.
 *
 * <p>If customer information is known, it can be prefilled.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class InitiateSessionRequestPrefillCustomer {
  /** Example: "Ada" */
  private String firstName;
  /** Example: "Lovelace" */
  private String lastName;
  /** Example: "user@example.com" */
  private String email;
  /** Format must be MSISDN (including country code). Example: "4791234567" */
  private String phoneNumber;
  /** Example: "Robert Levins gate 5" */
  private String streetAddress;
  /** Example: "Oslo" */
  private String city;
  /** Example: "0154" */
  private String postalCode;
  /** The ISO-3166-1 Alpha-2 representation of the country. Example: "NO" */
  private String country;
}
