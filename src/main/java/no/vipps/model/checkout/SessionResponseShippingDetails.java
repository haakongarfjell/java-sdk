/*
 * checkout-backend-merchant-v3.API
 * For details, see the [Checkout API Guide](https://vippsas.github.io/vipps-developer-docs/docs/APIs/checkout-api).
 *
 * The version of the OpenAPI document: v3
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** SessionResponseShippingDetails. */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class SessionResponseShippingDetails {
  /** Example: \&quot;Ada\&quot; */
  private String firstName;
  /** Example: \&quot;Lovelace\&quot; */
  private String lastName;
  /** Example: \&quot;user@example.com\&quot; */
  private String email;
  /** If no country code is provided, defaults to Norway (47). Example: \&quot;4791234567\&quot; */
  private String phoneNumber;
  /** Example: \&quot;Robert Levins gate 5\&quot; */
  private String streetAddress;
  /** Example: \&quot;0154\&quot; */
  private String postalCode;
  /** Example: \&quot;Oslo\&quot; */
  private String city;
  /** The ISO-3166-1 Alpha-2 representation of the country. Example: \&quot;NO\&quot; */
  private String country;
  /** Id of the shipping method. Example: \&quot;123abc\&quot; */
  private String shippingMethodId;
  /** */
  private ShippingDetailsPickupPoint pickupPoint;
}
