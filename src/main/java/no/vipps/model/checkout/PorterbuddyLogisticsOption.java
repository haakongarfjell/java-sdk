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

/** PorterbuddyLogisticsOption. */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PorterbuddyLogisticsOption {
  /** */
  private PorterbuddyLogisticsType type;
  /** */
  private String customType;
  /** */
  @Builder.Default private String brand = "PORTERBUDDY";
  /** */
  private LogisticsOptionBaseAmount amount;
  /** */
  private String id;
  /** */
  private Integer priority;
  /** */
  private Boolean isDefault;
  /** */
  private String description;
}
