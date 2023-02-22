package no.vipps.model.epayment.getpaymenteventlog;

import java.time.ZonedDateTime;
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
public class GetPaymentEventLog {
  private String reference;
  private String pspReference;
  private String name;
  private String paymentAction;
  private Amount amount;
  private ZonedDateTime timestamp;
  private ZonedDateTime processedAt;
  private String idempotencyKey;
  private boolean success;
}
