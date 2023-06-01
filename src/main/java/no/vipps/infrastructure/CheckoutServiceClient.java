package no.vipps.infrastructure;

import no.vipps.helpers.Constants;
import okhttp3.Headers;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class CheckoutServiceClient extends BaseServiceClient {

  public CheckoutServiceClient(VippsClient vippsHttpClient) {
    super(vippsHttpClient);
  }

  @Override
  Headers getHeaders() {
    return Headers.of(
        new HashMap<String, String>() {
          {
            put(Constants.HEADER_NAME_CLIENT_ID, VippsConfiguration.getInstance().getClientId());
            put(Constants.HEADER_NAME_CLIENT_SECRET, VippsConfiguration.getInstance().getClientSecret());
          }
        });
  }

  @Override
  CompletableFuture<Headers> getHeadersAsync() {
    return CompletableFuture.completedFuture(this.getHeaders());
  }
}
