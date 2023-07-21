package no.vipps.model.webhooks;

public class WebhookEvent {
  public static final String EPaymentCreated = "epayments.payment.created.v1";
  public static final String EPaymentAborted = "epayments.payment.aborted.v1";
  public static final String EPaymentExpired = "epayments.payment.expired.v1";
  public static final String EPaymentCancelled = "epayments.payment.cancelled.v1";
  public static final String EPaymentCaptured = "epayments.payment.captured.v1";
  public static final String EPaymentRefunded = "epayments.payment.refunded.v1";
  public static final String EPaymentAuthorized = "epayments.payment.authorized.v1";
  public static final String EPaymentTerminated = "epayments.payment.terminated.v1";
}
