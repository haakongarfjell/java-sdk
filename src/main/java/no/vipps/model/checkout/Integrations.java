package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** Integrations. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class Integrations {
  /** */
  private IntegrationsPorterbuddy porterbuddy;
  /** */
  private IntegrationsInstabox instabox;
  /** */
  private IntegrationsHelthjem helthjem;
}
