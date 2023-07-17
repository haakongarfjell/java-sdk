package no.vipps.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import no.vipps.helpers.UrlHelper;
import no.vipps.infrastructure.CheckoutServiceClient;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.SessionResponse;

public class CheckoutService {
  private static final ExecutorService executor = Executors.newSingleThreadExecutor();

  private final CheckoutServiceClient checkoutServiceClient;

  private final VippsConfigurationOptions vippsConfigurationOptions;

  public CheckoutService(
      CheckoutServiceClient checkoutServiceClient,
      VippsConfigurationOptions vippsConfigurationOptions) {
    this.checkoutServiceClient = checkoutServiceClient;
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  public InitiateSessionResponse initiateSession(InitiateSessionRequest initiateSessionRequest) {
    return checkoutServiceClient.executeRequest(
        getRequestPath(), "POST", initiateSessionRequest, InitiateSessionResponse.class);
  }

  public CompletableFuture<InitiateSessionResponse> initiateSessionAsync(
      InitiateSessionRequest initiateSessionRequest) {
    return checkoutServiceClient.executeRequestAsync(
        getRequestPath(), "POST", initiateSessionRequest, InitiateSessionResponse.class);
  }

  public SessionResponse getSessionInfo(String reference) {
    return checkoutServiceClient.executeRequest(
        getRequestPath(reference, ""), "GET", SessionResponse.class);
  }

  public CompletableFuture<SessionResponse> getSessionInfoAsync(String reference) {
    return checkoutServiceClient.executeRequestAsync(
        getRequestPath(reference, ""), "GET", SessionResponse.class);
  }

  private String getRequestPath() {
    return UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
        + "/checkout/v3/session/";
  }

  private String getRequestPath(String reference, String path) {
    StringBuilder requestPath =
        new StringBuilder(
            UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
                + "/checkout/v3/session/");
    if (!reference.isEmpty()) {
      requestPath.append("/").append(reference);
    }
    if (!path.isEmpty()) {
      requestPath.append("/").append(path);
    }
    return requestPath.toString();
  }
}
