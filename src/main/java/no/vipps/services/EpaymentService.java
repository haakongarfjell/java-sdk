package no.vipps.services;

import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.epayment.ModificationResponse;
import no.vipps.model.epayment.capturepayment.CapturePaymentRequest;
import no.vipps.model.epayment.createpayment.CreatePaymentRequest;
import no.vipps.model.epayment.createpayment.CreatePaymentResponse;
import no.vipps.model.epayment.forceapprove.ForceApproveRequest;
import no.vipps.model.epayment.getpayment.GetPaymentResponse;
import no.vipps.model.epayment.getpaymenteventlog.GetPaymentEventLog;
import no.vipps.model.epayment.refundpayment.RefundPaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpaymentService {
  private static Logger logger = LoggerFactory.getLogger(EpaymentService.class);

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

  public static ModificationResponse cancelPayment(String reference) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "cancel"), "POST", ModificationResponse.class);
  }

  public static ModificationResponse capturePayment(String reference,
                                                    CapturePaymentRequest capturePaymentRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "capture"), "POST", capturePaymentRequest,
            ModificationResponse.class);
  }

  public static ModificationResponse refundPayment(String reference,
                                                   RefundPaymentRequest refundPaymentRequest) {
    return VippsServices.getEpaymentServiceClient()
        .executeRequest(getRequestPath(reference, "refund"), "POST", refundPaymentRequest,
            ModificationResponse.class);
  }

  public static void forceApprovePayment(String reference,
                                         ForceApproveRequest forceApproveRequest) {
    VippsServices.getEpaymentServiceClient().executeRequest(
        (VippsConfiguration.getInstance().getBaseUrl() + "/epayment/v1/test/payments/" + reference +
            "/approve"), "POST", forceApproveRequest, null);
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
