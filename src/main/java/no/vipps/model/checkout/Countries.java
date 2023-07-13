package no.vipps.model.checkout;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** Countries. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class Countries {
  /**
   * List of allowed countries in ISO-3166 Alpha 2. If specified, the customer will only be able to
   * select these countries. Example ["NO", "SE", "DK"]
   */
  @Builder.Default private List<String> supported = new ArrayList<>();
}
