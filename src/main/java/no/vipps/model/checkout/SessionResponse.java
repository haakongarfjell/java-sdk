package no.vipps.model.checkout;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.Map;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * SessionResponse.
 *
 * <p>Session information
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class SessionResponse {
  /** The Id of the session. Example: "v52EtjZriRmGiKiAKHByK2". */
  private String sessionId;
  /** The merchant&#39;s serial number. Example: "123456" */
  private String merchantSerialNumber;
  /**
   * The merchant&#39;s unique reference for the transaction. Also known as OrderId. Example:
   * "acme-shop-123-order123abc". See
   * https://vippsas.github.io/vipps-developer-docs/docs/vipps-developers/common-topics/orderid
   */
  private String reference;
  /**
   * The state of the session. Example: "SessionStarted". The state of the payment is in
   * PaymentDetails.State.
   */
  private ExternalSessionState sessionState;
  /** */
  private PaymentMethod paymentMethod;
  /** */
  private SessionResponsePaymentDetails paymentDetails;
  /** */
  private SessionResponseUserInfo userInfo;
  /** */
  private SessionResponseShippingDetails shippingDetails;
  /** */
  private SessionResponseBillingDetails billingDetails;
  /** */
  private Boolean customConsentProvided;

  @Singular @JsonAnySetter @JsonAnyGetter private Map<String, Object> additionalProperties;
}
