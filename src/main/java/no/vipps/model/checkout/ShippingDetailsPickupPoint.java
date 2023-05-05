package no.vipps.model.checkout;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** ShippingDetailsPickupPoint. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class ShippingDetailsPickupPoint {
  /** Pickup point id provided by the carrier. Example: 121648 */
  private String id;
  /** Pickup point name. Example: Extra Eiganes */
  private String name;
  /** Pickup point&#39;s street address. Example: VITAMINVEIEN 7 */
  private String address;
  /** Pickup point&#39;s postal code. Example: 0485 */
  private String postalCode;
  /** Pickup point&#39;s city. Example: OSLO */
  private String city;
  /** Pickup point&#39;s country. Example: NO */
  private String country;
  /** Pickup point&#39;s opening hours. Example: Man-Søn: 1000-2000 */
  private List<String> openingHours;
  /** */
  private PickupPointInstabox instabox;
}
