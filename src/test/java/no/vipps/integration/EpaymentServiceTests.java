package no.vipps.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.epayment.Amount;
import no.vipps.model.epayment.CaptureModificationRequest;
import no.vipps.model.epayment.CreatePaymentRequest;
import no.vipps.model.epayment.CreatePaymentRequestUserFlow;
import no.vipps.model.epayment.CreatePaymentResponse;
import no.vipps.model.epayment.Currency;
import no.vipps.model.epayment.Customer;
import no.vipps.model.epayment.ForceApprove;
import no.vipps.model.epayment.GetPaymentResponse;
import no.vipps.model.epayment.ModificationResponse;
import no.vipps.model.epayment.PaymentEvent;
import no.vipps.model.epayment.PaymentEventName;
import no.vipps.model.epayment.PaymentMethod;
import no.vipps.model.epayment.PaymentMethodType;
import no.vipps.model.epayment.RefundModificationRequest;
import no.vipps.model.epayment.State;
import no.vipps.services.EpaymentService;
import org.junit.jupiter.api.Test;

public class EpaymentServiceTests {

  public EpaymentServiceTests() {
    var config = VippsConfigurationOptions.builder()
        .clientId(System.getProperty("CLIENT_ID"))
        .clientSecret(System.getProperty("CLIENT_SECRET"))
        .subscriptionKey(System.getProperty("SUBSCRIPTION_KEY"))
        .merchantSerialNumber(System.getProperty("MERCHANT_SERIAL_NUMBER"))
        .isUseTestMode(true)
        .pluginName("Vipps.net.IntegrationTests")
        .pluginVersion("1.0.0")
        .build();
    VippsConfiguration.getInstance().configureVipps(config, null);
  }

  private static final String CUSTOMER_PHONE_NUMBER = "4747753942";

  @Test
  public void testCanCreateGetCancelPayment() throws Exception {
    String reference = UUID.randomUUID().toString();
    CreatePaymentRequest createPaymentRequest = getCreatePaymentRequest(reference);
    CreatePaymentResponse createPaymentResponse = EpaymentService.createPayment(createPaymentRequest);
    assertNotNull(createPaymentResponse);
    assertEquals(reference, createPaymentResponse.getReference());

    ModificationResponse modificationResponse = EpaymentService.cancelPayment(reference);
    assertNotNull(modificationResponse);
    assertEquals(reference, modificationResponse.getReference());
    assertEquals(State.TERMINATED, modificationResponse.getState());

    GetPaymentResponse getPaymentResponse = EpaymentService.getPayment(reference);
    assertEquals(reference, getPaymentResponse.getReference());
    assertEquals(State.TERMINATED, getPaymentResponse.getState());
  }

  @Test
  public void testCanCreateApproveCaptureRefundPayment() throws Exception {
    String reference = UUID.randomUUID().toString();
    CreatePaymentRequest createPaymentRequest = getCreatePaymentRequest(reference);
    CreatePaymentResponse createPaymentResponse = EpaymentService.createPayment(createPaymentRequest);

    assertNotNull(createPaymentResponse);
    assertEquals(reference, createPaymentResponse.getReference());

    EpaymentService.forceApprovePayment(
        reference,
        ForceApprove.builder()
            .customer(Customer.builder().phoneNumber(CUSTOMER_PHONE_NUMBER).build())
            .build());

    CaptureModificationRequest capturePaymentRequest = CaptureModificationRequest.builder()
        .modificationAmount(createPaymentRequest.getAmount())
        .build();

    ModificationResponse captureResponse = EpaymentService.capturePayment(reference, capturePaymentRequest);
    assertNotNull(captureResponse);
    assertEquals(reference, captureResponse.getReference());
    assertEquals(State.AUTHORIZED, captureResponse.getState());

    RefundModificationRequest refundPaymentRequest = RefundModificationRequest.builder()
        .modificationAmount(createPaymentRequest.getAmount())
        .build();

    ModificationResponse modificationResponse = EpaymentService.refundPayment(reference, refundPaymentRequest);
    assertNotNull(modificationResponse);
    assertEquals(reference, modificationResponse.getReference());
    assertEquals(State.AUTHORIZED, modificationResponse.getState());

    PaymentEvent[] paymentEventsArray = EpaymentService.getPaymentEventLog(reference);
    assertNotNull(paymentEventsArray);
    List<PaymentEvent> paymentEvents = Arrays.asList(paymentEventsArray);
    assertOneEvent(paymentEvents, PaymentEventName.CREATED);
    assertOneEvent(paymentEvents, PaymentEventName.CREATED);
    assertOneEvent(paymentEvents, PaymentEventName.CAPTURED);
    assertOneEvent(paymentEvents, PaymentEventName.REFUNDED);
    assertOneEvent(paymentEvents, PaymentEventName.AUTHORIZED);
  }

  private void assertOneEvent(List<PaymentEvent> paymentEvents, PaymentEventName paymentEventName) {
    assertEquals(
        1, paymentEvents.stream().filter(r -> r.getName() == paymentEventName).toList().size());
  }

  private CreatePaymentRequest getCreatePaymentRequest(String reference) {
    return CreatePaymentRequest.builder()
        .amount(Amount.builder().currency(Currency.NOK).value(100L).build())
        .paymentMethod(PaymentMethod.builder().type(PaymentMethodType.WALLET).build())
        .userFlow(CreatePaymentRequestUserFlow.WEB_REDIRECT)
        .reference(reference)
        .paymentDescription("CheckoutServiceTests.Can_Create_And_Get_Session")
        .returnUrl("https://no.where.com/" + reference)
        .customer(Customer.builder().phoneNumber(CUSTOMER_PHONE_NUMBER).build())
        .build();
  }
}
