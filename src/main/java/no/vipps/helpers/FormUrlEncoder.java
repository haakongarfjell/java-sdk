package no.vipps.helpers;

import static java.util.stream.Collectors.joining;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.beanutils.PropertyUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;

public class FormUrlEncoder {
    private static final ObjectMapper objectMapper =
            new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .registerModule(new JavaTimeModule())
                    .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  public static String Encode(Object obj) {
      try {
        return URLEncoder.encode(objectMapper.writeValueAsString(obj), StandardCharsets.UTF_8.toString());
      } catch(JsonProcessingException e) {
          e.printStackTrace();
      } catch(UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      return null;
  }
}
