package no.vipps.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import java.util.Map;
import java.util.UUID;
import no.vipps.exceptions.VippsTechnicalException;
import no.vipps.helpers.VippsRequestSerializer;
import no.vipps.model.checkout.Elements;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionRequestConfiguration;
import no.vipps.model.checkout.InitiateSessionRequestTransaction;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.LogisticsOptionBaseAmount;
import no.vipps.model.epayment.CreatePaymentRequest;
import no.vipps.model.epayment.CreatePaymentRequestUserFlow;
import no.vipps.model.epayment.Currency;
import no.vipps.model.epayment.PaymentMethod;
import no.vipps.model.epayment.PaymentMethodType;
import org.junit.jupiter.api.Test;

public class VippsRequestSerializerTests {

  @Test
  public void canSerializeWithNestedExtraParameters() throws JsonProcessingException {
    InitiateSessionRequest initiateSessionRequest =
        InitiateSessionRequest.builder()
            .transaction(
                InitiateSessionRequestTransaction.builder()
                    .amount(
                        LogisticsOptionBaseAmount.builder().currency("NOK").value(49000).build())
                    .paymentDescription("Hei")
                    .build())
            .additionalProperties(
                Map.of("Transaction", Map.of("Metadata", Map.of("KID", "100001"))))
            .build();

    String serializedRequest = VippsRequestSerializer.serializeVippsRequest(initiateSessionRequest);
    assertNotNull(serializedRequest);
    assertNotEquals("", serializedRequest);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode deserialized = mapper.readTree(serializedRequest);
    assertNotNull(deserialized);

    JsonNode initiateSessionRequestAdditionalProperties = mapper.readTree(serializedRequest);

    String initiateSessionRequestKID =
        initiateSessionRequestAdditionalProperties
            .path("Transaction")
            .path("Metadata")
            .path("KID")
            .asText();

    String deserializedKID = deserialized.path("Transaction").path("Metadata").path("KID").asText();

    assertFalse((deserializedKID.isEmpty() || initiateSessionRequestKID.isEmpty()));
    assertEquals(initiateSessionRequestKID, deserializedKID);
  }

  @Test
  public void canSerializeWithExtraParametersArray() throws JsonProcessingException {
    InitiateSessionRequest initiateSessionRequest =
        InitiateSessionRequest.builder()
            .transaction(
                InitiateSessionRequestTransaction.builder()
                    .amount(
                        LogisticsOptionBaseAmount.builder().currency("NOK").value(49000).build())
                    .paymentDescription("Hei")
                    .build())
            .configuration(
                InitiateSessionRequestConfiguration.builder()
                    .elements(Elements.PAYMENTONLY)
                    .build())
            .additionalProperties(
                Map.of(
                    "Configuration",
                    Map.of("AcceptedPaymentMethods", new String[] {"WALLET", "CARD"})))
            .build();

    String serializedRequest = VippsRequestSerializer.serializeVippsRequest(initiateSessionRequest);
    assertNotNull(serializedRequest);
    assertFalse(serializedRequest.isEmpty());

    ObjectMapper mapper = new ObjectMapper();
    JsonNode deserialized = mapper.readTree(serializedRequest);
    assertNotNull(deserialized);

    JsonNode acceptedPaymentMethods =
        deserialized.get("Configuration").get("AcceptedPaymentMethods");
    assertNotNull(acceptedPaymentMethods);
    assertEquals(JsonNodeType.ARRAY, acceptedPaymentMethods.getNodeType());
  }

  @Test
  public void canSerializeWithExtraParametersOnUndefinedReceiver() throws JsonProcessingException {
    InitiateSessionRequest initiateSessionRequest =
        InitiateSessionRequest.builder()
            .transaction(
                InitiateSessionRequestTransaction.builder()
                    .amount(
                        LogisticsOptionBaseAmount.builder().currency("NOK").value(49000).build())
                    .paymentDescription("Hei")
                    .build())
            .additionalProperties(
                Map.of(
                    "Configuration",
                    Map.of("AcceptedPaymentMethods", new String[] {"WALLET", "CARD"})))
            .build();

    String serializedRequest = VippsRequestSerializer.serializeVippsRequest(initiateSessionRequest);
    assertNotNull(serializedRequest);
    assertFalse(serializedRequest.isEmpty());

    ObjectMapper mapper = new ObjectMapper();
    JsonNode deserialized = mapper.readTree(serializedRequest);
    assertNotNull(deserialized);

    JsonNode acceptedPaymentMethods =
        deserialized.get("Configuration").get("AcceptedPaymentMethods");
    assertNotNull(acceptedPaymentMethods);
    assertEquals(JsonNodeType.ARRAY, acceptedPaymentMethods.getNodeType());
  }

  @Test
  public void canDeserializeResponseWithoutExtraProperties() throws JsonProcessingException {
    InitiateSessionResponse initiateSessionResponse =
        InitiateSessionResponse.builder()
            .checkoutFrontendUrl("https://vipps.no/checkout-frontend")
            .pollingUrl("https://api.vipps.no/checkout/v3/session/reference101")
            .token("eynghsvdsjhkfgasf")
            .build();

    ObjectMapper objectMapper = new ObjectMapper();
    String serializedResponse = objectMapper.writeValueAsString(initiateSessionResponse);
    assertNotNull(serializedResponse);
    assertFalse(serializedResponse.isEmpty());
    var deserializedResponse =
        VippsRequestSerializer.deserializeVippsResponse(
            serializedResponse, InitiateSessionResponse.class);

    assertNotNull(deserializedResponse);
  }

  @Test
  public void canDeserializeResponseWithExtraProperties() throws JsonProcessingException {
    Map<String, String> initiateSessionResponse =
        Map.of(
            "checkoutFrontendUrl",
            "https://vipps.no/checkout-frontend",
            "pollingUrl",
            "https://api.vipps.no/checkout/v3/session/reference101",
            "token",
            "eynghsvdsjhkfgasf",
            "cancellationUrl",
            "https://api.vipps.no/checkout/v3/session/reference101/cancel");

    ObjectMapper objectMapper = new ObjectMapper();
    String serializedResponse = objectMapper.writeValueAsString(initiateSessionResponse);
    assertNotNull(serializedResponse);
    assertFalse(serializedResponse.isEmpty());
    var deserializedResponse =
        VippsRequestSerializer.deserializeVippsResponse(
            serializedResponse, InitiateSessionResponse.class);

    assertNotNull(deserializedResponse);
    assertEquals(
        "https://api.vipps.no/checkout/v3/session/reference101/cancel",
        deserializedResponse.getAdditionalProperties().get("cancellationUrl"));
  }

  @Test
  public void canDeserializeResponseWithNestedExtraProperties() throws JsonProcessingException {
    Map<String, Object> initiateSessionResponse =
        Map.of(
            "checkoutFrontendUrl",
            "https://vipps.no/checkout-frontend",
            "pollingUrl",
            "https://api.vipps.no/checkout/v3/session/reference101",
            "token",
            "eynghsvdsjhkfgasf",
            "epayment",
            Map.of(
                "pollingUrl",
                "https://api.vipps.no/checkout/v3/session/reference101",
                "captureUrl",
                "https://api.vipps.no/epayment/v1/payment/reference101/capture"));

    ObjectMapper objectMapper = new ObjectMapper();
    String serializedResponse = objectMapper.writeValueAsString(initiateSessionResponse);
    assertNotNull(serializedResponse);
    assertFalse(serializedResponse.isEmpty());
    var deserializedResponse =
        VippsRequestSerializer.deserializeVippsResponse(
            serializedResponse, InitiateSessionResponse.class);
    Map<String, Object> epayment =
        (Map<String, Object>) deserializedResponse.getAdditionalProperties().get("epayment");
    assertNotNull(deserializedResponse);
    assertEquals(
        "https://api.vipps.no/checkout/v3/session/reference101", epayment.get("pollingUrl"));
    assertEquals(
        "https://api.vipps.no/epayment/v1/payment/reference101/capture",
        epayment.get("captureUrl"));
  }

  @Test
  public void deserializationGivenInvalidDataThrowsException() {
    assertThrows(
        VippsTechnicalException.class,
        () -> {
          VippsRequestSerializer.deserializeVippsResponse("invalid", InitiateSessionResponse.class);
        });
  }

  @Test
  public void canDeserializeResponseWithExtraPropertiesEpayment() throws JsonProcessingException {
    String cancellationUrl = "https://api.vipps.no/checkout/v3/session/reference101/cancel";
    Map<String, Object> createPaymentRequest =
        Map.of(
            "Amount",
            no.vipps.model.epayment.Amount.builder().currency(Currency.NOK).value(1000L).build(),
            "PaymentMethod",
            no.vipps.model.epayment.PaymentMethod.builder().type(PaymentMethodType.WALLET),
            "Reference",
            UUID.randomUUID().toString(),
            "UserFlow",
            CreatePaymentRequestUserFlow.WEB_REDIRECT,
            "cancellationUrl",
            cancellationUrl);

    ObjectMapper objectMapper = new ObjectMapper();
    String serializedResponse = objectMapper.writeValueAsString(createPaymentRequest);

    CreatePaymentRequest deserializedResponse =
        VippsRequestSerializer.deserializeVippsResponse(
            serializedResponse, CreatePaymentRequest.class);
    assertNotNull(deserializedResponse);
    assertNotNull(deserializedResponse.getAdditionalProperties());
    assertFalse((deserializedResponse.getAdditionalProperties().isEmpty()));
    assertEquals(
        deserializedResponse.getAdditionalProperties().get("cancellationUrl"), cancellationUrl);
  }

  @Test
  public void canSerializeResponseWithExtraPropertiesEpayment() throws JsonProcessingException {
    String kid = "100001";
    CreatePaymentRequest createPaymentRequest =
        CreatePaymentRequest.builder()
            .amount(
                no.vipps.model.epayment.Amount.builder()
                    .currency(Currency.NOK)
                    .value(1000L)
                    .build())
            .paymentMethod(PaymentMethod.builder().type(PaymentMethodType.WALLET).build())
            .reference(UUID.randomUUID().toString())
            .userFlow(CreatePaymentRequestUserFlow.WEB_REDIRECT)
            .additionalProperties(Map.of("Transaction", Map.of("Metadata", Map.of("KID", kid))))
            .build();

    ObjectMapper objectMapper = new ObjectMapper();
    String serializedRequest = objectMapper.writeValueAsString(createPaymentRequest);
    assertNotNull(serializedRequest);
  }
}
