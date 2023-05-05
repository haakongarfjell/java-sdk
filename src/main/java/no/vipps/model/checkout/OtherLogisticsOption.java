package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** OtherLogisticsOption. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class OtherLogisticsOption {
  /** */
  private String title;
  /** */
  @Builder.Default private String brand = "OTHER";
  /** */
  private LogisticsOptionBaseAmount amount;
  /** */
  private String id;
  /** */
  private Integer priority;
  /** */
  private Boolean isDefault;
  /** */
  private String description;
}
