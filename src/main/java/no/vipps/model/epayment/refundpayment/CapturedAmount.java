package no.vipps.model.epayment.refundpayment;

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
public class CapturedAmount {
  private final String currency;
  private final int value;
}
