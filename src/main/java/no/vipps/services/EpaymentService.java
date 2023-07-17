package no.vipps.services;

import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.UrlHelper;
import no.vipps.infrastructure.EpaymentServiceClient;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.epayment.CaptureModificationRequest;
import no.vipps.model.epayment.CreatePaymentRequest;
import no.vipps.model.epayment.CreatePaymentResponse;
import no.vipps.model.epayment.ForceApprove;
import no.vipps.model.epayment.GetPaymentResponse;
import no.vipps.model.epayment.ModificationResponse;
import no.vipps.model.epayment.PaymentEvent;
import no.vipps.model.epayment.RefundModificationRequest;

public class EpaymentService {

  private final EpaymentServiceClient epaymentServiceClient;

  private final VippsConfigurationOptions vippsConfigurationOptions;

  public EpaymentService(
      EpaymentServiceClient epaymentServiceClient,
      VippsConfigurationOptions vippsConfigurationOptions) {
    this.epaymentServiceClient = epaymentServiceClient;
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  public CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
    return epaymentServiceClient.executeRequest(
        getRequestPath(), "POST", createPaymentRequest, CreatePaymentResponse.class);
  }

  public CompletableFuture<CreatePaymentResponse> createPaymentAsync(
      CreatePaymentRequest createPaymentRequest) {
    return epaymentServiceClient.executeRequestAsync(
        getRequestPath(), "POST", createPaymentRequest, CreatePaymentResponse.class);
  }

  public GetPaymentResponse getPayment(String reference) {
    return epaymentServiceClient.executeRequest(
        getRequestPath(reference, ""), "GET", GetPaymentResponse.class);
  }

  public CompletableFuture<GetPaymentResponse> getPaymentAsync(String reference) {
    return epaymentServiceClient.executeRequestAsync(
        getRequestPath(reference, ""), "GET", GetPaymentResponse.class);
  }

  public PaymentEvent[] getPaymentEventLog(String reference) {
    return epaymentServiceClient.executeRequest(
        getRequestPath(reference, "events"), "GET", PaymentEvent[].class);
  }

  public CompletableFuture<PaymentEvent[]> getPaymentEventLogAsync(String reference) {
    return epaymentServiceClient.executeRequestAsync(
        getRequestPath(reference, "events"), "GET", PaymentEvent[].class);
  }

  public ModificationResponse cancelPayment(String reference) {
    return epaymentServiceClient.executeRequest(
        getRequestPath(reference, "cancel"), "POST", ModificationResponse.class);
  }

  public CompletableFuture<ModificationResponse> cancelPaymentAsync(String reference) {
    return epaymentServiceClient.executeRequestAsync(
        getRequestPath(reference, "cancel"), "POST", ModificationResponse.class);
  }

  public ModificationResponse capturePayment(
      String reference, CaptureModificationRequest captureModificationRequest) {
    return epaymentServiceClient.executeRequest(
        getRequestPath(reference, "capture"),
        "POST",
        captureModificationRequest,
        ModificationResponse.class);
  }

  public CompletableFuture<ModificationResponse> capturePaymentAsync(
      String reference, CaptureModificationRequest captureModificationRequest) {
    return epaymentServiceClient.executeRequestAsync(
        getRequestPath(reference, "capture"),
        "POST",
        captureModificationRequest,
        ModificationResponse.class);
  }

  public ModificationResponse refundPayment(
      String reference, RefundModificationRequest refundModificationRequest) {
    return epaymentServiceClient.executeRequest(
        getRequestPath(reference, "refund"),
        "POST",
        refundModificationRequest,
        ModificationResponse.class);
  }

  public CompletableFuture<ModificationResponse> refundPaymentAsync(
      String reference, RefundModificationRequest refundModificationRequest) {
    return epaymentServiceClient.executeRequestAsync(
        getRequestPath(reference, "refund"),
        "POST",
        refundModificationRequest,
        ModificationResponse.class);
  }

  public void forceApprovePayment(String reference, ForceApprove forceApproveRequest) {
    epaymentServiceClient.executeRequest(
        (UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
            + "/epayment/v1/test/payments/"
            + reference
            + "/approve"),
        "POST",
        forceApproveRequest,
        null);
  }

  public CompletableFuture<Void> forceApprovePaymentAsync(
      String reference, ForceApprove forceApproveRequest) {
    return epaymentServiceClient.executeRequestAsync(
        (UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
            + "/epayment/v1/test/payments/"
            + reference
            + "/approve"),
        "POST",
        forceApproveRequest,
        null);
  }

  private String getRequestPath(String reference, String path) {
    StringBuilder requestPath =
        new StringBuilder(
            UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
                + "/epayment/v1/payments");
    if (!reference.isEmpty()) {
      requestPath.append("/").append(reference);
    }
    if (!path.isEmpty()) {
      requestPath.append("/").append(path);
    }
    return requestPath.toString();
  }

  private String getRequestPath() {
    return UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
        + "/epayment/v1/payments";
  }
}
