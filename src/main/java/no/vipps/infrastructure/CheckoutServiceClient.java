package no.vipps.infrastructure;

import java.util.Map;
import no.vipps.helpers.Constants;
import okhttp3.Headers;

public class CheckoutServiceClient extends BaseServiceClient {

  public CheckoutServiceClient(VippsClient vippsHttpClient) {
    super(vippsHttpClient);
  }

  @Override
  public Headers getHeaders() {
    return Headers.of(Map.of(
        Constants.HEADER_NAME_CLIENT_ID, VippsConfiguration.getInstance().getClientId(),
        Constants.HEADER_NAME_CLIENT_SECRET, VippsConfiguration.getInstance().getClientSecret()
    ));
  }
}
