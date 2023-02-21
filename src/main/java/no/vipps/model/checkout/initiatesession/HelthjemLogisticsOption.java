package no.vipps.model.checkout.initiatesession;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class HelthjemLogisticsOption extends LogisticsOptionBase {
  private final HelthjemLogisticsType type;
  private final String customType;
}
