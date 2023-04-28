package no.vipps.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import no.vipps.infrastructure.VippsConfiguration;
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

  @BeforeAll
  public static void Authenticate() {
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
    VippsConfiguration.getInstance().configureVipps(config, null);
  }

  @Test
  public void testCanCreateAndGetSession() {
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
        CheckoutService.initiateSession(sessionInitiationRequest);
    assertNotNull(sessionResponse);

    SessionResponse sessionPolledResponse = CheckoutService.getSessionInfo(reference);
    assertEquals(ExternalSessionState.SessionCreated, sessionPolledResponse.getSessionState());
  }
}
