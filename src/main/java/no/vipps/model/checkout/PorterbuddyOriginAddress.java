package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * PorterbuddyOriginAddress.
 *
 * <p>Your address where Porterbuddy will pick up the parcels
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PorterbuddyOriginAddress {
  /** Example: "Robert Levins gate 5" */
  private String streetAddress;
  /** Example: "0154" */
  private String postalCode;
  /** Example: "Oslo" */
  private String city;
  /** The ISO-3166-1 Alpha-2 representation of the country. Example: "NO" */
  private String country;
}
