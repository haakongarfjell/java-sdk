/*
 * Vipps ePayment API
 * The Vipps ePayment API. See the [API Guide](https://vippsas.github.io/vipps-developer-docs/docs/APIs/epayment-api) for more information.
 *
 * The version of the OpenAPI document: 1.1.2
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package no.vipps.model.epayment;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.Map;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** PaymentMethodResponse. */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PaymentMethodResponse {
  /** */
  private PaymentMethodType type;
  /** */
  private String cardBin;

  @Singular @JsonAnySetter @JsonAnyGetter private Map<String, Object> additionalProperties;
}
