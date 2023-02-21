package no.vipps.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.json.JSONObject;

@Jacksonized
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class VippsResponse {
  @JsonIgnore
  public JSONObject rawResponse;
}
