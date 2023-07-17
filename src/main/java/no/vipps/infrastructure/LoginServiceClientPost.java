package no.vipps.infrastructure;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import okhttp3.Headers;

public class LoginServiceClientPost extends BaseServiceClient {

  private final VippsConfigurationOptions vippsConfigurationOptions;

  public LoginServiceClientPost(
      VippsClient vippsClient, VippsConfigurationOptions vippsConfigurationOptions) {
    super(vippsClient);
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  @Override
  Headers getHeaders() {
    return Headers.of(new HashMap<String, String>());
  }

  @Override
  CompletableFuture<Headers> getHeadersAsync() {
    return CompletableFuture.completedFuture(this.getHeaders());
  }
}
