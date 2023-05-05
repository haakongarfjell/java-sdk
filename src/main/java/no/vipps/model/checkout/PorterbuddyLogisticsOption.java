package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** PorterbuddyLogisticsOption. */
@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@Builder
public class PorterbuddyLogisticsOption extends LogisticsOptionBase {
  /** */
  private PorterbuddyLogisticsType type;
  /** */
  private String customType;
  /** */
  @Builder.Default private String brand = "PORTERBUDDY";
  /** */
  private Amount amount;
  /** */
  private String id;
  /** */
  private Integer priority;
  /** */
  private Boolean isDefault;
  /** */
  private String description;
}
