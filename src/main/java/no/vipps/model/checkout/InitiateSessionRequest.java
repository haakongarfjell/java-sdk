package no.vipps.model.checkout;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * InitiateSessionRequest.
 *
 * <p>Request to set up a Checkout session
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitiateSessionRequest {
  /** */
  private InitiateSessionRequestMerchantInfo merchantInfo;
  /** */
  private InitiateSessionRequestTransaction transaction;
  /** */
  private InitiateSessionRequestLogistics logistics;
  /** */
  private InitiateSessionRequestPrefillCustomer prefillCustomer;
  /** */
  private InitiateSessionRequestConfiguration configuration;

  @Singular @JsonAnySetter @JsonAnyGetter private Map<String, Object> additionalProperties;
}
