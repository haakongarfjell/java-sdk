package no.vipps.model.epayment.createpayment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@EqualsAndHashCode
@Getter
@ToString
@Builder(toBuilder = true)
public class OrderLine {
  public final String name;
  public final String id;
  public final Integer totalAmount;
  public final Integer totalAmountExcludingTax;
  public final Integer totalTaxAmount;
  public final Integer taxPercentage;
  public final UnitInfo unitInfo;
  public final Integer discount;
  public final String productUrl;
  public final Boolean isReturn;
  public final Boolean isShipping;
}
