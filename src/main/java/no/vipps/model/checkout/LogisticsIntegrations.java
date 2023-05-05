package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * LogisticsIntegrations.
 *
 * <p>Some optional checkout features require carrier-specific configuration. Can not be used with
 * AddressFields set to false.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class LogisticsIntegrations {
  /** */
  private IntegrationsPorterbuddy porterbuddy;
  /** */
  private IntegrationsInstabox instabox;
  /** */
  private IntegrationsHelthjem helthjem;
}
