package no.vipps.helpers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.vipps.exceptions.VippsTechnicalException;

public class VippsRequestSerializer {
  private static final ObjectMapper objectMapper =
      new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
          .registerModule(new JavaTimeModule())
          .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  public static <T> String serializeVippsRequest(T vippsRequest) {
    try {
      return objectMapper.writeValueAsString(vippsRequest);
    } catch (JsonProcessingException e) {
      throw new VippsTechnicalException("Error serializing request", e);
    }
  }

  public static <T> T deserializeVippsResponse(String vippsResponse, Class<T> responseType) {
    try {
      T deserializedTyped = objectMapper.readValue(vippsResponse, responseType);
      if (deserializedTyped == null) {
        throw new VippsTechnicalException(
            "Response could not be deserialized to " + responseType.getSimpleName());
      }
      return deserializedTyped;
    } catch (Exception e) {
      throw new VippsTechnicalException(
          "Error deserializing response of type " + responseType.getSimpleName(), e);
    }
  }
}
