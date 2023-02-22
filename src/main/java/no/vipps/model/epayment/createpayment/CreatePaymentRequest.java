package no.vipps.model.epayment.createpayment;

import java.time.ZonedDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import no.vipps.model.VippsRequest;

@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class CreatePaymentRequest extends VippsRequest {
  public final Amount amount;
  public final boolean directCapture;
  public final Customer customer;
  public final String customerInteraction;
  public final IndustryData industryData;
  public final Receipt receipt;
  public final PaymentMethod paymentMethod;
  public final Profile profile;
  public final String reference;
  public final String returnUrl;
  public final String userFlow;
  public final ZonedDateTime expiresAt;
  public final QrFormat qrFormat;
  public final String paymentDescription;
}
