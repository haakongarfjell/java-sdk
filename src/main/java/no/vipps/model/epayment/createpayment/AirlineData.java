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
public class AirlineData {
  public final String agencyInvoiceNumber;
  public final String airlineCode;
  public final String airlineDesignatorCode;
  public final String passengerName;
  public final String ticketNumber;
}
