package no.vipps.helpers;

import static java.util.stream.Collectors.joining;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
            JsonNode jsonNode = objectMapper.valueToTree(obj);
            StringBuilder formUrl = new StringBuilder();
            encodeNode(formUrl, "", jsonNode);
            return formUrl.toString();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void encodeNode(StringBuilder formUrl, String prefix, JsonNode node) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            objectNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();
                String encodedFieldName = encodeValue(fieldName);
                String prefixedFieldName = prefix.isEmpty() ? encodedFieldName : prefix + "." + encodedFieldName;
                encodeNode(formUrl, prefixedFieldName, fieldValue);
            });
        } else if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode element = arrayNode.get(i);
                String indexedFieldName = prefix + "[" + i + "]";
                encodeNode(formUrl, indexedFieldName, element);
            }
        } else {
            String fieldName = prefix;
            String fieldValue = node.isNull() ? null : node.asText();
            if (fieldValue != null) {
                String encodedFieldName = encodeValue(fieldName);
                String encodedFieldValue = encodeValue(fieldValue);
                formUrl.append(encodedFieldName).append("=").append(encodedFieldValue).append("&");
            }
        }
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

