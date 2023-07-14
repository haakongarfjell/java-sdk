package no.vipps.infrastructure;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.Constants;
import okhttp3.Headers;

public class AccessTokenServiceClient extends BaseServiceClient {

  public AccessTokenServiceClient(VippsHttpClient vippsHttpClient) {
    super(vippsHttpClient);
  }

  @Override
  Headers getHeaders() {
    return Headers.of(
        new HashMap<String, String>() {
          {
            put(Constants.HEADER_NAME_CLIENT_ID, VippsConfiguration.getInstance().getClientId());
            put(
                Constants.HEADER_NAME_CLIENT_SECRET,
                VippsConfiguration.getInstance().getClientSecret());
            put(Constants.SUBSCRIPTION_KEY, VippsConfiguration.getInstance().getSubscriptionKey());
          }
        });
  }

  @Override
  CompletableFuture<Headers> getHeadersAsync() {
    return CompletableFuture.completedFuture(this.getHeaders());
  }
}
