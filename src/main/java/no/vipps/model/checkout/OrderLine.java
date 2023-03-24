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

/** OrderLine. */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class OrderLine {
  /** The name of the product in the order line. */
  private String name;
  /** The product ID. */
  private String id;
  /**
   * Total amount of the order line, including tax and discount. Must be in Minor Units. The
   * smallest unit of a currency. Example 100 NOK &#x3D; 10000.
   */
  private Long totalAmount;
  /**
   * Total amount of order line with discount excluding tax. Must be in Minor Units. The smallest
   * unit of a currency. Example 100 NOK &#x3D; 10000.
   */
  private Long totalAmountExcludingTax;
  /**
   * Total tax amount paid for the order line. Must be in Minor Units. The smallest unit of a
   * currency. Example 100 NOK &#x3D; 10000.
   */
  private Long totalTaxAmount;
  /** Tax percentage for the order line. */
  private Integer taxPercentage;
  /** */
  private OrderLineUnitInfo unitInfo;
  /**
   * Total discount for the order line. Must be in Minor Units. The smallest unit of a currency.
   * Example 100 NOK &#x3D; 10000.
   */
  private Long discount;
  /** URL linking back to the product at the merchant. */
  private String productUrl;
  /**
   * Flag for marking the orderline as returned. This will make it count negative towards all the
   * sums in BottomLine.
   */
  private Boolean isReturn;
  /**
   * Flag for marking the orderline as a shipping line. This will be shown differently in the app.
   */
  private Boolean isShipping;
}
