package no.vipps.model.checkout.initiatesession;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class LogisticsOptionBase {
  @NotNull
  private final Amount amount;

  @NotNull
  private final String id;

  private final int priority;

  private final boolean isDefault;

  private final String description;
}
