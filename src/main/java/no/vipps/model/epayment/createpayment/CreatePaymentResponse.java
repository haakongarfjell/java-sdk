package no.vipps.model.epayment.createpayment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import no.vipps.model.VippsResponse;

@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class CreatePaymentResponse extends VippsResponse {

  public final String redirectUrl;


  public final String Reference;
}
