package no.vipps.integration;

import no.vipps.VippsApi;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.epayment.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EpaymentServiceTests {

  private static final String CUSTOMER_PHONE_NUMBER = "4747753942";

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
  public void can_create_get_cancel_payment() {
    String reference = UUID.randomUUID().toString();
    CreatePaymentRequest createPaymentRequest = getCreatePaymentRequest(reference);
    CreatePaymentResponse createPaymentResponse =
        vippsApi.epaymentService().createPayment(createPaymentRequest);
    assertNotNull(createPaymentResponse);
    assertEquals(reference, createPaymentResponse.getReference());

    ModificationResponse modificationResponse = vippsApi.epaymentService().cancelPayment(reference);
    assertNotNull(modificationResponse);
    assertEquals(reference, modificationResponse.getReference());
    assertEquals(State.TERMINATED, modificationResponse.getState());

    GetPaymentResponse getPaymentResponse = vippsApi.epaymentService().getPayment(reference);
    assertEquals(reference, getPaymentResponse.getReference());
    assertEquals(State.TERMINATED, getPaymentResponse.getState());
  }

  @Test
  public void can_create_get_cancel_payment_async()
      throws ExecutionException, InterruptedException {
    String reference = UUID.randomUUID().toString();
    CreatePaymentRequest createPaymentRequest = getCreatePaymentRequest(reference);
    CreatePaymentResponse createPaymentResponse =
        vippsApi.epaymentService().createPaymentAsync(createPaymentRequest).get();

    assertNotNull(createPaymentResponse);
    assertEquals(reference, createPaymentResponse.getReference());

    ModificationResponse modificationResponse =
        vippsApi.epaymentService().cancelPaymentAsync(reference).get();
    assertNotNull(modificationResponse);
    assertEquals(reference, modificationResponse.getReference());
    assertEquals(State.TERMINATED, modificationResponse.getState());

    GetPaymentResponse getPaymentResponse =
        vippsApi.epaymentService().getPaymentAsync(reference).get();
    assertEquals(reference, getPaymentResponse.getReference());
    assertEquals(State.TERMINATED, getPaymentResponse.getState());
  }

  @Test
  public void can_create_approve_capture_refund_payment() {
    String reference = UUID.randomUUID().toString();
    CreatePaymentRequest createPaymentRequest = getCreatePaymentRequest(reference);
    CreatePaymentResponse createPaymentResponse =
        vippsApi.epaymentService().createPayment(createPaymentRequest);

    assertNotNull(createPaymentResponse);
    assertEquals(reference, createPaymentResponse.getReference());

    vippsApi
        .epaymentService()
        .forceApprovePayment(
            reference,
            ForceApprove.builder()
                .customer(Customer.builder().phoneNumber(CUSTOMER_PHONE_NUMBER).build())
                .build());

    CaptureModificationRequest capturePaymentRequest =
        CaptureModificationRequest.builder()
            .modificationAmount(createPaymentRequest.getAmount())
            .build();

    ModificationResponse captureResponse =
        vippsApi.epaymentService().capturePayment(reference, capturePaymentRequest);
    assertNotNull(captureResponse);
    assertEquals(reference, captureResponse.getReference());
    assertEquals(State.AUTHORIZED, captureResponse.getState());

    RefundModificationRequest refundPaymentRequest =
        RefundModificationRequest.builder()
            .modificationAmount(createPaymentRequest.getAmount())
            .build();

    ModificationResponse modificationResponse =
        vippsApi.epaymentService().refundPayment(reference, refundPaymentRequest);
    assertNotNull(modificationResponse);
    assertEquals(reference, modificationResponse.getReference());
    assertEquals(State.AUTHORIZED, modificationResponse.getState());

    PaymentEvent[] paymentEventsArray = vippsApi.epaymentService().getPaymentEventLog(reference);
    assertNotNull(paymentEventsArray);
    List<PaymentEvent> paymentEvents = Arrays.asList(paymentEventsArray);
    assertOneEvent(paymentEvents, PaymentEventName.CREATED);
    assertOneEvent(paymentEvents, PaymentEventName.CREATED);
    assertOneEvent(paymentEvents, PaymentEventName.CAPTURED);
    assertOneEvent(paymentEvents, PaymentEventName.REFUNDED);
    assertOneEvent(paymentEvents, PaymentEventName.AUTHORIZED);
  }

  @Test
  public void can_create_approve_capture_refund_payment_async()
      throws ExecutionException, InterruptedException {
    String reference = UUID.randomUUID().toString();
    CreatePaymentRequest createPaymentRequest = getCreatePaymentRequest(reference);
    CreatePaymentResponse createPaymentResponse =
        vippsApi.epaymentService().createPaymentAsync(createPaymentRequest).get();

    assertNotNull(createPaymentResponse);
    assertEquals(reference, createPaymentResponse.getReference());

    vippsApi
        .epaymentService()
        .forceApprovePaymentAsync(
            reference,
            ForceApprove.builder()
                .customer(Customer.builder().phoneNumber(CUSTOMER_PHONE_NUMBER).build())
                .build())
        .get();

    CaptureModificationRequest capturePaymentRequest =
        CaptureModificationRequest.builder()
            .modificationAmount(createPaymentRequest.getAmount())
            .build();

    ModificationResponse captureResponse =
        vippsApi.epaymentService().capturePaymentAsync(reference, capturePaymentRequest).get();
    assertNotNull(captureResponse);
    assertEquals(reference, captureResponse.getReference());
    assertEquals(State.AUTHORIZED, captureResponse.getState());

    RefundModificationRequest refundPaymentRequest =
        RefundModificationRequest.builder()
            .modificationAmount(createPaymentRequest.getAmount())
            .build();

    ModificationResponse modificationResponse =
        vippsApi.epaymentService().refundPaymentAsync(reference, refundPaymentRequest).get();
    assertNotNull(modificationResponse);
    assertEquals(reference, modificationResponse.getReference());
    assertEquals(State.AUTHORIZED, modificationResponse.getState());

    PaymentEvent[] paymentEventsArray =
        vippsApi.epaymentService().getPaymentEventLogAsync(reference).get();
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
        1, paymentEvents.stream().filter(r -> r.getName() == paymentEventName).toArray().length);
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
