package no.vipps.model.checkout;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class MerchantAuthInfoHeaders {
  @JsonProperty("client_id")
  private final String clientId;

  @JsonProperty("client_secret")
  private final String clientSecret;

  @JsonProperty("Ocp-Apim-Subscription-Key")
  private final String ocpApimSubscriptionKey;

  @JsonProperty("Merchant-Serial-Number")
  private final String merchantSerialNumber;
}
