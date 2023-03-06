package no.vipps.services;

import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.epayment.cancelpayment.CancelPaymentResponse;
import no.vipps.model.epayment.capturepayment.CapturePaymentRequest;
import no.vipps.model.epayment.capturepayment.CapturePaymentResponse;
import no.vipps.model.epayment.createpayment.CreatePaymentRequest;
import no.vipps.model.epayment.createpayment.CreatePaymentResponse;
import no.vipps.model.epayment.forceapprove.ForceApproveRequest;
import no.vipps.model.epayment.getpayment.GetPaymentResponse;
import no.vipps.model.epayment.getpaymenteventlog.GetPaymentEventLog;
import no.vipps.model.epayment.refundpayment.RefundPaymentResponse;

public class EpaymentService {
  public static CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(), "POST", createPaymentRequest,
            CreatePaymentResponse.class);
  }

  public static GetPaymentResponse getPayment(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, ""), "GET", GetPaymentResponse.class);
  }

  public static Iterable<GetPaymentEventLog> getPaymentEventLog(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "events"), "GET", Iterable.class);
  }

  public static CancelPaymentResponse cancelPayment(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "cancel"), "POST", CancelPaymentResponse.class);
  }

  public static CapturePaymentResponse capturePayment(CapturePaymentRequest capturePaymentRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath("", "capture"), "POST", capturePaymentRequest,
            CapturePaymentResponse.class);
  }

  public static RefundPaymentResponse refundPayment(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "refund"), "POST", RefundPaymentResponse.class);
  }

  public static void forceApprovePayment(String reference,
                                         ForceApproveRequest forceApproveRequest) {
    VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "approve"), "POST", forceApproveRequest, null);
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
