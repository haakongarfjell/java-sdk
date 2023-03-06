package no.vipps.model.epayment.createpayment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder(toBuilder = true)
public class Amount {
  public final String currency;
  public final Integer value;
}