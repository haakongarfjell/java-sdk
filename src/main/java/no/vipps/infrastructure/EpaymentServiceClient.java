package no.vipps.infrastructure;

import java.util.HashMap;
import java.util.UUID;
import no.vipps.helpers.Constants;
import no.vipps.services.AccessTokenService;
import okhttp3.Headers;

public class EpaymentServiceClient extends BaseServiceClient {

  public EpaymentServiceClient(VippsClient vippsClient) {
    super(vippsClient);
  }

  @Override
  public Headers getHeaders() {
    String authToken = AccessTokenService.getAccessToken().toString();
    HashMap<String, String> headers = new HashMap<>();
    headers.put(Constants.HEADER_NAME_AUTHORIZATION,
        Constants.AUTHORIZATION_SCHEME_NAME_BEARER + " " + authToken);
    headers.put("Idempotency-Key", UUID.randomUUID().toString());
    return Headers.of(headers);
  }
}
