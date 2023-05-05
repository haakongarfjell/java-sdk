package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** PostenLogisticsOption. */
@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@Builder
public class PostenLogisticsOption extends LogisticsOptionBase {
  /** */
  private PostenLogisticsType type;
  /** */
  private String customType;
  /** */
  @Builder.Default private String brand = "POSTEN";
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
