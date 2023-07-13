package no.vipps.model.checkout;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** InitiateSessionRequestLogistics. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class InitiateSessionRequestLogistics {
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
