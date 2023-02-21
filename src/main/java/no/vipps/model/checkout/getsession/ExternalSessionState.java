package no.vipps.model.checkout.getsession;

public enum ExternalSessionState {
  SessionCreated,
  PaymentInitiated,
  SessionExpired,
  PaymentSuccessful,
  PaymentTerminated
}
