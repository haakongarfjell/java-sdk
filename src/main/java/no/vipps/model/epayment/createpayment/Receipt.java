package no.vipps.model.epayment.createpayment;

import java.util.List;
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
public class Receipt {
  public final List<OrderLine> orderLines;
  public final BottomLine bottomLine;
}
