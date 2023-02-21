package no.vipps.model.checkout.getsession;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.jetbrains.annotations.NotNull;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class UserInfo {
  @NotNull
  private final String sub;
  private final String email;
}
