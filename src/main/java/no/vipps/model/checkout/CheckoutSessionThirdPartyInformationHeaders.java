package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * CheckoutSessionThirdPartyInformationHeaders.
 *
 * <p>Information about the merchant system.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class CheckoutSessionThirdPartyInformationHeaders {
  /** The name of the ecommerce solution. Example: "Acme Enterprises Ecommerce DeLuxe". */
  private String vippsSystemName;
  /** The version number of the ecommerce solution. Example: "3.1.2". */
  private String vippsSystemVersion;
  /** The name of the ecommerce plugin. Example: "acme-webshop". */
  private String vippsSystemPluginName;
  /** The version number of the ecommerce plugin. Example: "4.5.6". */
  private String vippsSystemPluginVersion;
}
