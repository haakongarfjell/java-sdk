package no.vipps.model.checkout;

/** ExternalSessionState. */
public enum ExternalSessionState {

  /** "SessionCreated" */
  SessionCreated,

  /** "PaymentInitiated" */
  PaymentInitiated,

  /** "SessionExpired" */
  SessionExpired,

  /** "PaymentSuccessful" */
  PaymentSuccessful,

  /** "PaymentTerminated" */
  PaymentTerminated,
}
