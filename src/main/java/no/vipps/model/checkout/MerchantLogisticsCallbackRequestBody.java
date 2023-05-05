package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * MerchantLogisticsCallbackRequestBody.
 *
 * <p>Information about the customer address used when retrieving dynamic logistics options.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class MerchantLogisticsCallbackRequestBody {
  /** Example: "Robert Levins gate 5" */
  private String streetAddress;
  /** Example: "0154" */
  private String postalCode;
  /** Example: "Oslo" */
  private String region;
  /** The ISO-3166-1 Alpha-2 representation of the country. Example: "NO" */
  private String country;
}
