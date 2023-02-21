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
public class PickupPoint {

  private final String id;

  private final String name;

  private final String postalCode;

  private final String city;

  private final String country;
}
