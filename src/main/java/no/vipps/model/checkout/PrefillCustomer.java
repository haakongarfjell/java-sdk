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

/**
 * PrefillCustomer.
 *
 * <p>Information about the customer to be prefilled If any of the customer information is invalid
 * such as the phone number, the customer will be prompted to input new user information.
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PrefillCustomer {
  /** Example: \&quot;Ada\&quot; */
  private String firstName;
  /** Example: \&quot;Lovelace\&quot; */
  private String lastName;
  /** Example: \&quot;user@example.com\&quot; */
  private String email;
  /** Format must be MSISDN (including country code). Example: \&quot;4791234567\&quot; */
  private String phoneNumber;
  /** Example: \&quot;Robert Levins gate 5\&quot; */
  private String streetAddress;
  /** Example: \&quot;Oslo\&quot; */
  private String city;
  /** Example: \&quot;0154\&quot; */
  private String postalCode;
  /** The ISO-3166-1 Alpha-2 representation of the country. Example: \&quot;NO\&quot; */
  private String country;
}
