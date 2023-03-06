package no.vipps.infrastructure;

import java.util.HashMap;
import no.vipps.helpers.Constants;
import okhttp3.Headers;

public class CheckoutServiceClient extends BaseServiceClient {

  public CheckoutServiceClient(VippsClient vippsHttpClient) {
    super(vippsHttpClient);
  }

  @Override
  public Headers getHeaders() {
    return Headers.of(new HashMap<String, String>() {
      {
        put(Constants.HEADER_NAME_CLIENT_ID, VippsConfiguration.getInstance().getClientId());
        put(Constants.HEADER_NAME_CLIENT_SECRET,
            VippsConfiguration.getInstance().getClientSecret());
      }
    });
  }
}
