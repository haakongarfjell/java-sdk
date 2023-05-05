package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** SessionResponseShippingDetails. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class SessionResponseShippingDetails {
  /** Example: "Ada" */
  private String firstName;
  /** Example: "Lovelace" */
  private String lastName;
  /** Example: "user@example.com" */
  private String email;
  /** If no country code is provided, defaults to Norway (47). Example: "4791234567" */
  private String phoneNumber;
  /** Example: "Robert Levins gate 5" */
  private String streetAddress;
  /** Example: "0154" */
  private String postalCode;
  /** Example: "Oslo" */
  private String city;
  /** The ISO-3166-1 Alpha-2 representation of the country. Example: "NO" */
  private String country;
  /** Id of the shipping method. Example: "123abc" */
  private String shippingMethodId;
  /** */
  private ShippingDetailsPickupPoint pickupPoint;
}
