package no.vipps.services;


import no.vipps.infrastructure.CheckoutServiceClient;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.checkout.getsession.GetSessionInfoResponse;
import no.vipps.model.checkout.initiatesession.InitiateSessionRequest;
import no.vipps.model.checkout.initiatesession.InitiateSessionResponse;

public class CheckoutService {
  public static void initiateSession(InitiateSessionRequest initiateSessionRequest) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath = VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session";

    InitiateSessionResponse response =
        checkoutServiceClient.<InitiateSessionRequest, InitiateSessionResponse>executeRequest(
            requestPath, "POST", initiateSessionRequest, InitiateSessionResponse.class);
  }

  public static GetSessionInfoResponse getSessionInfo(String reference) {
    CheckoutServiceClient checkoutServiceClient = VippsServices.getCheckoutServiceClient();
    String requestPath = VippsConfiguration.getInstance().getBaseUrl() + "/checkout/v3/session";
    return checkoutServiceClient.executeRequest(requestPath, "GET", GetSessionInfoResponse.class);
  }
}
