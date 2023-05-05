package no.vipps.model.checkout;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Logistics.
 *
 * <p>If both dynamic and fixed options are specified, dynamic options is provided to the user. If
 * no DynamicOptionsCallback is provided, only fixed logistics options will be used. When using
 * dynamic shipping we recommend that you define logistics.fixedOptions as a backup. If the callback
 * does not resolve successfully within 8 seconds, returns null or an empty list the system will
 * fall back to static options. If no fallback options are provided, the user will be presented with
 * an error and will not be able to continue with the checkout.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class Logistics {
  /**
   * Merchant&#39;s Callback URL for providing dynamic logistics options based on customer address.
   * Example: "https://example.com/vipps/dynamiclogisticsoption". Can not be used with AddressFields
   * set to false.
   */
  private String dynamicOptionsCallback;
  /** Fixed list of logistics options. */
  private List<LogisticsOptionBase> fixedOptions;
  /** */
  private LogisticsIntegrations integrations;
}
