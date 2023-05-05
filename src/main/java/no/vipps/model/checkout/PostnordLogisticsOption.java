package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** PostnordLogisticsOption. */
@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@Builder
public class PostnordLogisticsOption extends LogisticsOptionBase {
  /** */
  private PostnordLogisticsType type;
  /** */
  private String customType;
  /** */
  @Builder.Default private String brand = "POSTNORD";
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
