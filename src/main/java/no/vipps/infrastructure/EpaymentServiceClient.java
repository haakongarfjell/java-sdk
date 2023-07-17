package no.vipps.infrastructure;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.Constants;
import no.vipps.services.AccessTokenService;
import okhttp3.Headers;

public class EpaymentServiceClient extends BaseServiceClient {

  private final VippsConfigurationOptions vippsConfigurationOptions;

  private final AccessTokenService accessTokenService;

  public EpaymentServiceClient(
      VippsClient vippsClient,
      AccessTokenService accessTokenService,
      VippsConfigurationOptions vippsConfigurationOptions) {
    super(vippsClient);
    this.vippsConfigurationOptions = vippsConfigurationOptions;
    this.accessTokenService = accessTokenService;
  }

  @Override
  Headers getHeaders() {
    String authToken = accessTokenService.getAccessToken().getToken();
    HashMap<String, String> headers = new HashMap<>();
    headers.put(
        Constants.HEADER_NAME_AUTHORIZATION,
        Constants.AUTHORIZATION_SCHEME_NAME_BEARER + " " + authToken);
    headers.put(Constants.SUBSCRIPTION_KEY, vippsConfigurationOptions.getSubscriptionKey());
    headers.put("Idempotency-Key", UUID.randomUUID().toString());
    return Headers.of(headers);
  }

  @Override
  CompletableFuture<Headers> getHeadersAsync() {
    return accessTokenService
        .getAccessTokenAsync()
        .thenApply(
            (accessToken -> {
              HashMap<String, String> headers = new HashMap<>();
              headers.put(
                  Constants.HEADER_NAME_AUTHORIZATION,
                  Constants.AUTHORIZATION_SCHEME_NAME_BEARER + " " + accessToken.getToken());
              headers.put(
                  Constants.SUBSCRIPTION_KEY, vippsConfigurationOptions.getSubscriptionKey());
              headers.put("Idempotency-Key", UUID.randomUUID().toString());
              return Headers.of(headers);
            }));
  }
}
