package no.vipps.model.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/** PaymentTransaction. */
@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class PaymentTransaction {
  /** */
  private LogisticsOptionBaseAmount amount;
  /**
   * The merchant&#39;s unique reference for the transaction. Also known as OrderId. Example:
   * "acme-shop-123-order123abc". See
   * https://vippsas.github.io/vipps-developer-docs/docs/vipps-developers/common-topics/orderid
   */
  private String reference;
  /** Description visible to the customer during payment. Example: "One pair of Vipps socks". */
  private String paymentDescription;
  /** */
  private PaymentTransactionOrderSummary orderSummary;
}
