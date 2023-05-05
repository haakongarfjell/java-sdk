package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** HelthjemLogisticsOption. */
@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@Builder
public class HelthjemLogisticsOption extends LogisticsOptionBase {
  /** */
  private HelthjemLogisticsType type;
  /** */
  private String customType;
  /** */
  @Builder.Default private String brand = "HELTHJEM";
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
