package no.vipps.model.checkout;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * ResponsePaymentDetails.
 *
 * <p>Defines the details of the payment.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class ResponsePaymentDetails {
  /** */
  private ResponsePaymentDetailsAmount amount;
  /** */
  private PaymentState state;
  /** */
  private ResponsePaymentDetailsAggregate aggregate;

  @Singular @JsonAnySetter @JsonAnyGetter private Map<String, Object> additionalProperties;
}
