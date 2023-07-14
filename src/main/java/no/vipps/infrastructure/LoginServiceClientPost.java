package no.vipps.infrastructure;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import okhttp3.Headers;

public class LoginServiceClientPost extends BaseServiceClient {

  public LoginServiceClientPost(VippsClient vippsClient) {
    super(vippsClient);
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
