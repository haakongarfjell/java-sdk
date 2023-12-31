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

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * AirlineData.
 *
 * <p>Airline related data. If present, &#x60;passengerName&#x60;, &#x60;airlineCode&#x60;,
 * &#x60;airlineDesignatorCode&#x60;, and &#x60;agencyInvoiceNumber&#x60; are all required.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class AirlineData {
  /** Reference number for the invoice, issued by the agency. */
  private String agencyInvoiceNumber;
  /** IATA 3-digit accounting code (PAX); numeric. It identifies the carrier. eg KLM &#x3D; 074 */
  private String airlineCode;
  /**
   * IATA 2-letter accounting code (PAX); alphabetical. It identifies the carrier. Eg KLM &#x3D; KL
   */
  private String airlineDesignatorCode;
  /** Passenger name, initials, and a title. */
  private String passengerName;
  /** The ticket&#39;s unique identifier. */
  private String ticketNumber;
}
