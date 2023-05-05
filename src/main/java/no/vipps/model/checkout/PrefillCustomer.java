package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * PrefillCustomer.
 *
 * <p>Information about the customer to be prefilled If any of the customer information is invalid
 * such as the phone number, the customer will be prompted to input new user information.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PrefillCustomer {
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
