package no.vipps.model.checkout.getsession;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class BillingDetails {

  private final String firstName;

  private final String lastName;

  private final String email;

  private final String phoneNumber;

  private final String streetAddress;

  private final String postalCode;

  private final String region;

  private final String country;
}
