package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** CheckoutConfig. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class CheckoutConfig {
  /** If customer is physically present: "customer_present", otherwise: "customer_not_present". */
  private CustomerInteraction customerInteraction;
  /** Adjust the fields and values present in the Checkout. */
  private Elements elements;
  /** */
  private CheckoutConfigCountries countries;
  /**
   * One of the following: "WEB_REDIRECT", "NATIVE_REDIRECT". To ensure having a return URL based on
   * an app URL, use "NATIVE_REDIRECT".
   */
  private UserFlow userFlow;
  /**
   * Requires the customer to consent to share their email and openid sub with the merchant to be
   * able to make a wallet payment (default: false).
   */
  private Boolean requireUserInfo;
  /** */
  private CheckoutConfigCustomConsent customConsent;
  /** Decides whether the order lines are displayed as a shopping cart context in the checkout. */
  private Boolean showOrderSummary;
}
