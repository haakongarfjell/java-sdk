package no.vipps.infrastructure;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.Constants;
import okhttp3.Headers;

public class CheckoutServiceClient extends BaseServiceClient {

  private final VippsConfigurationOptions vippsConfigurationOptions;

  public CheckoutServiceClient(
      VippsClient vippsClient, VippsConfigurationOptions vippsConfigurationOptions) {
    super(vippsClient);
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  @Override
  Headers getHeaders() {
    return Headers.of(
        new HashMap<String, String>() {
          {
            put(Constants.HEADER_NAME_CLIENT_ID, vippsConfigurationOptions.getClientId());
            put(Constants.HEADER_NAME_CLIENT_SECRET, vippsConfigurationOptions.getClientSecret());
            put(Constants.SUBSCRIPTION_KEY, vippsConfigurationOptions.getSubscriptionKey());
          }
        });
  }

  @Override
  CompletableFuture<Headers> getHeadersAsync() {
    return CompletableFuture.completedFuture(this.getHeaders());
  }
}
