package no.vipps.services;

import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.epayment.CaptureModificationRequest;
import no.vipps.model.epayment.CreatePaymentRequest;
import no.vipps.model.epayment.CreatePaymentResponse;
import no.vipps.model.epayment.ForceApprove;
import no.vipps.model.epayment.GetPaymentResponse;
import no.vipps.model.epayment.ModificationResponse;
import no.vipps.model.epayment.PaymentEvent;
import no.vipps.model.epayment.RefundModificationRequest;

import java.util.concurrent.CompletableFuture;

public class EpaymentService {

  public static CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(
            getRequestPath(), "POST", createPaymentRequest, CreatePaymentResponse.class);
  }

  public static CompletableFuture<CreatePaymentResponse> createPaymentAsync(CreatePaymentRequest createPaymentRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequestAsync(
            getRequestPath(), "POST", createPaymentRequest, CreatePaymentResponse.class);
  }

  public static GetPaymentResponse getPayment(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, ""), "GET", GetPaymentResponse.class);
  }

  public static CompletableFuture<GetPaymentResponse> getPaymentAsync(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequestAsync(getRequestPath(reference, ""), "GET", GetPaymentResponse.class);
  }

  public static PaymentEvent[] getPaymentEventLog(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "events"), "GET", PaymentEvent[].class);
  }

  public static CompletableFuture<PaymentEvent[]> getPaymentEventLogAsync(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequestAsync(getRequestPath(reference, "events"), "GET", PaymentEvent[].class);
  }

  public static ModificationResponse cancelPayment(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "cancel"), "POST", ModificationResponse.class);
  }

  public static CompletableFuture<ModificationResponse> cancelPaymentAsync(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequestAsync(getRequestPath(reference, "cancel"), "POST", ModificationResponse.class);
  }

  public static ModificationResponse capturePayment(
      String reference, CaptureModificationRequest captureModificationRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(
            getRequestPath(reference, "capture"),
            "POST",
            captureModificationRequest,
            ModificationResponse.class);
  }

  public static CompletableFuture<ModificationResponse> capturePaymentAsync(
      String reference, CaptureModificationRequest captureModificationRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequestAsync(
            getRequestPath(reference, "capture"),
            "POST",
            captureModificationRequest,
            ModificationResponse.class);
  }

  public static ModificationResponse refundPayment(
      String reference, RefundModificationRequest refundModificationRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(
            getRequestPath(reference, "refund"),
            "POST",
            refundModificationRequest,
            ModificationResponse.class);
  }

  public static CompletableFuture<ModificationResponse> refundPaymentAsync(
      String reference, RefundModificationRequest refundModificationRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequestAsync(
            getRequestPath(reference, "refund"),
            "POST",
            refundModificationRequest,
            ModificationResponse.class);
  }

  public static void forceApprovePayment(String reference, ForceApprove forceApproveRequest) {
    VippsServices.getEpaymentServiceClient()
        .executeRequest(
            (VippsConfiguration.getInstance().getBaseUrl()
                + "/epayment/v1/test/payments/"
                + reference
                + "/approve"),
            "POST",
            forceApproveRequest,
            null);
  }

  public static CompletableFuture<Void> forceApprovePaymentAsync(String reference, ForceApprove forceApproveRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequestAsync(
            (VippsConfiguration.getInstance().getBaseUrl()
                + "/epayment/v1/test/payments/"
                + reference
                + "/approve"),
            "POST",
            forceApproveRequest,
            null);
  }

  private static String getRequestPath(String reference, String path) {
    StringBuilder requestPath =
        new StringBuilder(VippsConfiguration.getInstance().getBaseUrl() + "/epayment/v1/payments");
    if (!reference.isEmpty()) {
      requestPath.append("/").append(reference);
    }
    if (!path.isEmpty()) {
      requestPath.append("/").append(path);
    }
    return requestPath.toString();
  }

  private static String getRequestPath() {
    return VippsConfiguration.getInstance().getBaseUrl() + "/epayment/v1/payments";
  }
}
