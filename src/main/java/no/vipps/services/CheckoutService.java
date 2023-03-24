package no.vipps.services;


import no.vipps.infrastructure.CheckoutServiceClient;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.SessionResponse;

public class CheckoutService {
  public static InitiateSessionResponse initiateSession(
      InitiateSessionRequest initiateSessionRequest) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath = VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session";

    return checkoutServiceClient.executeRequest(requestPath, "POST", initiateSessionRequest,
        InitiateSessionResponse.class);
  }

  public static SessionResponse getSessionInfo(String reference) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath = VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session";
    return checkoutServiceClient.executeRequest(requestPath, "GET", SessionResponse.class);
  }
}
