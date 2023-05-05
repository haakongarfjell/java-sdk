package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * IntegrationsHelthjem.
 *
 * <p>Configuration required to enable Helthjem logistics options
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class IntegrationsHelthjem {
  /** The Username provided to you by Helthjem */
  private String username;
  /** The Password provided to you by Helthjem */
  private String password;
  /** The ShopId provided to you by Helthjem */
  private Integer shopId;
}
