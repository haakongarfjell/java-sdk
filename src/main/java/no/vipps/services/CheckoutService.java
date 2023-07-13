package no.vipps.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import no.vipps.infrastructure.CheckoutServiceClient;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.SessionResponse;

public class CheckoutService {
  private static final ExecutorService executor = Executors.newSingleThreadExecutor();

  public static InitiateSessionResponse initiateSession(
      InitiateSessionRequest initiateSessionRequest) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath = VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session";

    return checkoutServiceClient.executeRequest(
        requestPath, "POST", initiateSessionRequest, InitiateSessionResponse.class);
  }

  public static CompletableFuture<InitiateSessionResponse> initiateSessionAsync(
      InitiateSessionRequest initiateSessionRequest) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath = VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session";

    return checkoutServiceClient.executeRequestAsync(
        requestPath, "POST", initiateSessionRequest, InitiateSessionResponse.class);
  }

  public static SessionResponse getSessionInfo(String reference) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath =
        VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session/" + reference;
    return checkoutServiceClient.executeRequest(requestPath, "GET", SessionResponse.class);
  }

  public static CompletableFuture<SessionResponse> getSessionInfoAsync(String reference) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath =
        VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session/" + reference;

    return checkoutServiceClient.executeRequestAsync(requestPath, "GET", SessionResponse.class);
  }
}
