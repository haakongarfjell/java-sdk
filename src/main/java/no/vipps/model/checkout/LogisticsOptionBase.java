package no.vipps.model.checkout;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/** LogisticsOptionBase. */
@EqualsAndHashCode
@Getter
@ToString
public abstract class LogisticsOptionBase {
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
