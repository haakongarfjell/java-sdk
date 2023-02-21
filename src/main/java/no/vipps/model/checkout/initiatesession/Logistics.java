package no.vipps.model.checkout.initiatesession;

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

public class Logistics {

  private final String receiverName;

  private final String receiverAddress;

  private final String receiverPostalCode;

  private final String receiverCity;

  private final String receiverCountry;
}
