package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * MerchantAuthInfoHeaders.
 *
 * <p>Headers required to retrieve an access token.
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class MerchantAuthInfoHeaders {
  /**
   * Client ID for the merchant (the "username"). Found in the Vipps portal. Example:
   * "fb492b5e-7907-4d83-bc20-c7fb60ca35de".
   */
  private String clientId;
  /**
   * Client Secret for the merchant (the "password"). Found in the Vipps portal. Example:
   * "Y8Kteew6GE3ZmeycEt6egg&#x3D;&#x3D;".
   */
  private String clientSecret;
  /**
   * Vipps Subscription key for the API product. Found in the Vipps portal. Example:
   * "0f14ebcab0eb4b29ae0cb90d91b4a84a".
   */
  private String ocpApimSubscriptionKey;
  /** Vipps assigned unique number for a merchant. Found in the Vipps portal. Example: "123456". */
  private String merchantSerialNumber;
}
