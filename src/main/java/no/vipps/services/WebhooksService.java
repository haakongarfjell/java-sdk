package no.vipps.services;

import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.UrlHelper;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.infrastructure.WebhooksServiceClient;
import no.vipps.model.webhooks.*;

public class WebhooksService {

  private final WebhooksServiceClient webhooksServiceClient;
  private final VippsConfigurationOptions vippsConfigurationOptions;

  public WebhooksService(
      WebhooksServiceClient webhooksServiceClient,
      VippsConfigurationOptions vippsConfigurationOptions) {
    this.webhooksServiceClient = webhooksServiceClient;
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  public RegisterResponse CreateWebHook(RegisterRequest registerRequest) {
    return webhooksServiceClient.executeRequest(
        getRequestPath(), "POST", registerRequest, RegisterResponse.class);
  }

  public CompletableFuture<RegisterResponse> CreateWebHookAsync(RegisterRequest registerRequest) {
    return webhooksServiceClient.executeRequestAsync(
        getRequestPath(), "POST", registerRequest, RegisterResponse.class);
  }

  public QueryResponse GetWebhooks() {
    return webhooksServiceClient.executeRequest(getRequestPath(), "GET", QueryResponse.class);
  }

  public CompletableFuture<QueryResponse> GetWebhooksAsync() {
    return webhooksServiceClient.executeRequestAsync(getRequestPath(), "GET", QueryResponse.class);
  }

  public void DeleteWebhook(String webhookId) {
    webhooksServiceClient.executeRequest(getRequestPath() + webhookId, "DELETE", Object.class);
  }

  private String getRequestPath() {
    return UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
        + "/webhooks/v1/webhooks/";
  }
}
