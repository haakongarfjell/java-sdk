package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * InstaboxBookingDetails.
 *
 * <p>Details needed to book an instabox order
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class InstaboxBookingDetails {
  /** Identifies when the delivery options were fetched */
  private String availabilityToken;
  /** Identifies the service (For example "EXPRESS") */
  private String serviceType;
  /** Identifies the location */
  private String sortCode;
}
