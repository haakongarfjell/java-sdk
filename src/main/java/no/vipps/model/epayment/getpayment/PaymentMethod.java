package no.vipps.model.epayment.getpayment;

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
public class PaymentMethod {
  private final String type;
  private final String cardBin;
}
