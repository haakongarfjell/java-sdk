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

/**
 * State.
 *
 * <p>State of the Payment. One of: - CREATED : User has not yet acted upon the payment - ABORTED :
 * User has aborted the payment before authorization - EXPIRED: User did not act on the payment
 * within the payment expiration time - AUTHORIZED : User has approved the payment - TERMINATED :
 * Merchant has terminated the payment via the cancelPayment endpoint
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public enum State {

  /** &quot;CREATED&quot; */
  CREATED,

  /** &quot;ABORTED&quot; */
  ABORTED,

  /** &quot;EXPIRED&quot; */
  EXPIRED,

  /** &quot;AUTHORIZED&quot; */
  AUTHORIZED,

  /** &quot;TERMINATED&quot; */
  TERMINATED,
}