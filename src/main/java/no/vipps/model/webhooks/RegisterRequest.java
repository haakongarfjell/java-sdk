package no.vipps.model.webhooks;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class RegisterRequest {

  @JsonProperty("url")
  private final String url;

  @JsonProperty("events")
  private final Collection<String> events;
}
