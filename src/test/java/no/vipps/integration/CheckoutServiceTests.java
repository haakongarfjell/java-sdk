package no.vipps.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import no.vipps.VippsApi;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.checkout.Amount;
import no.vipps.model.checkout.ExternalSessionState;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionRequestMerchantInfo;
import no.vipps.model.checkout.InitiateSessionRequestTransaction;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.SessionResponse;
import no.vipps.services.CheckoutService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CheckoutServiceTests {

  private static VippsApi vippsApi;

  @BeforeAll
  public static void authenticate() {
    Dotenv dotenv = Dotenv.configure().load();
    VippsConfigurationOptions config = VippsConfigurationOptions.builder()
            .clientId(dotenv.get("CLIENT_ID"))
            .clientSecret(dotenv.get("CLIENT_SECRET"))
            .subscriptionKey(dotenv.get("OCP_APIM_SUBSCRIPTION_KEY"))
            .merchantSerialNumber(dotenv.get("MSN"))
            .pluginName("Java-Sdk-Demo")
            .pluginVersion("1.0.0")
            .isUseTestMode(true)
            .build();
    vippsApi = VippsApi.Create(config);
  }

  @Test
  public void can_create_and_get_session() {
    String reference = UUID.randomUUID().toString();
    InitiateSessionRequest sessionInitiationRequest =
        InitiateSessionRequest.builder()
            .transaction(
                InitiateSessionRequestTransaction.builder()
                    .amount(Amount.builder().currency("NOK").value(1000).build())
                    .reference(reference)
                    .paymentDescription("CheckoutServiceTests.Can_Create_And_Get_Session")
                    .build())
            .merchantInfo(
                InitiateSessionRequestMerchantInfo.builder()
                    .callbackAuthorizationToken(UUID.randomUUID().toString())
                    .callbackUrl("https://no.where.com/callback")
                    .returnUrl("https://no.where.com/return")
                    .termsAndConditionsUrl("https://no.where.com/terms")
                    .build())
            .build();

    InitiateSessionResponse sessionResponse =
        vippsApi.checkoutService().initiateSession(sessionInitiationRequest);
    assertNotNull(sessionResponse);

    SessionResponse sessionPolledResponse = vippsApi.checkoutService().getSessionInfo(reference);
    assertEquals(ExternalSessionState.SessionCreated, sessionPolledResponse.getSessionState());
  }

  @Test
  public void can_create_and_get_session_async() throws ExecutionException, InterruptedException {
    String reference = UUID.randomUUID().toString();
    InitiateSessionRequest sessionInitiationRequest =
        InitiateSessionRequest.builder()
            .transaction(
                InitiateSessionRequestTransaction.builder()
                    .amount(Amount.builder().currency("NOK").value(1000).build())
                    .reference(reference)
                    .paymentDescription("CheckoutServiceTests.Can_Create_And_Get_Session")
                    .build())
            .merchantInfo(
                InitiateSessionRequestMerchantInfo.builder()
                    .callbackAuthorizationToken(UUID.randomUUID().toString())
                    .callbackUrl("https://no.where.com/callback")
                    .returnUrl("https://no.where.com/return")
                    .termsAndConditionsUrl("https://no.where.com/terms")
                    .build())
            .build();

    CompletableFuture<InitiateSessionResponse> sessionResponse =
            vippsApi.checkoutService().initiateSessionAsync(sessionInitiationRequest);
    assertNotNull(sessionResponse.get());

    CompletableFuture<SessionResponse> sessionPolledResponse =
            vippsApi.checkoutService().getSessionInfoAsync(reference);
    assertEquals(
        ExternalSessionState.SessionCreated, sessionPolledResponse.get().getSessionState());
  }
}
