package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * PorterbuddyOrigin.
 *
 * <p>Information about the sender
 */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PorterbuddyOrigin {
  /** The name of your store */
  private String name;
  /** Your email address where Porterbuddy booking confirmation will be sent */
  private String email;
  /**
   * Your phone number where Porterbuddy may send you important messages. Format must be MSISDN
   * (including country code). Example: "4791234567"
   */
  private String phoneNumber;
  /** */
  private PorterbuddyOriginAddress address;
}
