package no.vipps.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import no.vipps.VippsApi;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.webhooks.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WebhooksServiceTests {

  private static VippsApi vippsApi;

  @BeforeAll
  public static void authenticate() {
    VippsConfigurationOptions config =
        VippsConfigurationOptions.builder()
            .clientId(System.getenv("CLIENT_ID"))
            .clientSecret(System.getenv("CLIENT_SECRET"))
            .subscriptionKey(System.getenv("OCP_APIM_SUBSCRIPTION_KEY"))
            .merchantSerialNumber(System.getenv("MSN"))
            .pluginName("Java-Sdk-Demo")
            .pluginVersion("1.0.0")
            .isUseTestMode(true)
            .build();
    vippsApi = VippsApi.Create(config);
  }

  @Test
  public void CreateGetDeleteWebhook() {
    RegisterRequest registerRequest =
        RegisterRequest.builder()
            .url("https://webhook.site/ebcd8e27-5031-4dd5-beb7-a2d491cd7641")
            .events(List.of(WebhookEvent.EPaymentCreated))
            .build();

    RegisterResponse createWebhookResponse =
        vippsApi.webhooksService().CreateWebHook(registerRequest);
    assertNotNull(createWebhookResponse);
    assertNotNull(createWebhookResponse.getId());
    assertNotNull(createWebhookResponse.getSecret());

    QueryResponse getWebHooksResponse = vippsApi.webhooksService().GetWebhooks();

    assertTrue(
        getWebHooksResponse
            .getWebhooks()
            .contains(
                Webhook.builder()
                    .id(createWebhookResponse.getId())
                    .events(registerRequest.getEvents())
                    .url(registerRequest.getUrl())
                    .build()));

    vippsApi.webhooksService().DeleteWebhook(createWebhookResponse.getId());
  }
}
