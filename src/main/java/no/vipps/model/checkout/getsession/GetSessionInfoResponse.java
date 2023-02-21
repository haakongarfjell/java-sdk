package no.vipps.model.checkout.getsession;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import no.vipps.model.VippsResponse;


@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class GetSessionInfoResponse extends VippsResponse {
  @NotNull
  private final String sessionId;
  private final String merchantSerialNumber;
  @NotNull
  private final ExternalSessionState sessionState;
  private final PaymentMethod paymentMethod;
  private final ResponsePaymentDetails paymentDetails;
  private final UserInfo userInfo;
  private final ShippingDetails shippingDetails;
  private final BillingDetails billingDetails;

  @NotNull
  private final String reference;
}
