package no.vipps.integration;

import no.vipps.VippsApi;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.checkout.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckoutServiceTests {

  private static VippsApi vippsApi;

  @BeforeAll
  public static void authenticate() {
    VippsConfigurationOptions config =
        VippsConfigurationOptions.builder()
              .clientId(System.getenv("CLIENT_ID"))
              .clientSecret(System.getenv("CLIENT_SECRET"))
              .subscriptionKey(System.getenv("SUBSCRIPTION_KEY"))
              .merchantSerialNumber(System.getenv("MERCHANT_SERIAL_NUMBER"))
              .isUseTestMode(true)
              .pluginName("Vipps.net.IntegrationTests")
              .pluginVersion("1.0.0")
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
