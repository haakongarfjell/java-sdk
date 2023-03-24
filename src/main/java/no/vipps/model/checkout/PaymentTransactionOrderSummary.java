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

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * PaymentTransactionOrderSummary.
 *
 * <p>Contain descriptions of each item present in the order, and an order bottom line for
 * information regarding the order as a whole.
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PaymentTransactionOrderSummary {
  /** The order lines contain descriptions of each item present in the order. */
  private List<OrderLine> orderLines;
  /** */
  private OrderSummaryOrderBottomLine orderBottomLine;
}
