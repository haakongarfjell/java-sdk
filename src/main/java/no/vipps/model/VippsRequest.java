package no.vipps.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
public class VippsRequest {

  @JsonIgnore
  private String extraParameters;

  @JsonProperty("extraParameters")
  public String getSerializedExtraParameters() {
    return extraParameters;
  }
}
