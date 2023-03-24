package no.vipps.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import no.vipps.model.checkout.ExternalSessionState;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionRequestMerchantInfo;
import no.vipps.model.checkout.InitiateSessionRequestTransaction;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.LogisticsOptionBaseAmount;
import no.vipps.model.checkout.SessionResponse;
import no.vipps.services.CheckoutService;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;

public class CheckoutServiceTests {
  @Test
  public void testCanCreateAndGetSession() {
    String reference = UUID.randomUUID().toString();
    InitiateSessionRequest sessionInitiationRequest = InitiateSessionRequest.builder().transaction(
            InitiateSessionRequestTransaction.builder()
                .amount(LogisticsOptionBaseAmount.builder().currency("NOK").value(1000).build())
                .reference(reference)
                .paymentDescription("CheckoutServiceTests.Can_Create_And_Get_Session").build())
        .merchantInfo(InitiateSessionRequestMerchantInfo.builder()
            .callbackAuthorizationToken(UUID.randomUUID().toString())
            .callbackUrl("https://no.where.com/callback").returnUrl("https://no.where.com/return")
            .termsAndConditionsUrl("https://no.where.com/terms").build()).build();

    InitiateSessionResponse sessionResponse =
        CheckoutService.initiateSession(sessionInitiationRequest);
    assertNotNull(sessionResponse);

    SessionResponse sessionPolledResponse = CheckoutService.getSessionInfo(reference);
    assertEquals(ExternalSessionState.SESSIONCREATED, sessionPolledResponse.getSessionState());
  }
}
