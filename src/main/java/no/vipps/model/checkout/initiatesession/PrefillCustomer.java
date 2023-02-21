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
public class PrefillCustomer {

  private final String mobileNumber;

  private final String email;

  private final String name;
}
