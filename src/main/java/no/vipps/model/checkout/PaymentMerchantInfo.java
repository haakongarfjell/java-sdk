package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** PaymentMerchantInfo. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PaymentMerchantInfo {
  /** Complete URL for receiving callbacks. Example: "https://exmaple.com/vipps/payment-callback/ */
  private String callbackUrl;
  /**
   * Complete URL for redirecting customers to when the checkout is finished. Example:
   * "https://example.com/vipps".
   */
  private String returnUrl;
  /**
   * The token will be supplied by the callback to the merchant as a header. Example:
   * "iOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImllX3FXQ1hoWHh0MXpJ".
   */
  private String callbackAuthorizationToken;
  /**
   * Complete URL to the merchant&#39;s terms and conditions. Example:
   * "https://example.com/vipps/termsAndConditions".
   */
  private String termsAndConditionsUrl;
}
