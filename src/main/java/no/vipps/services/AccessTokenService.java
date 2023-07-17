package no.vipps.services;

import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.UrlHelper;
import no.vipps.infrastructure.AccessTokenServiceClient;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.accesstoken.AccessToken;

public class AccessTokenService {

  private final VippsConfigurationOptions vippsConfigurationOptions;

  private final AccessTokenServiceClient accessTokenServiceClient;

  public AccessTokenService(
      AccessTokenServiceClient accessTokenServiceClient,
      VippsConfigurationOptions vippsConfigurationOptions) {
    this.accessTokenServiceClient = accessTokenServiceClient;
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  public AccessToken getAccessToken() {
    String key =
        vippsConfigurationOptions.getClientId() + vippsConfigurationOptions.getClientSecret();
    AccessToken cachedToken = AccessTokenCacheService.get(key);

    if (cachedToken != null) {
      return cachedToken;
    }

    String requestPath =
        UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode()) + "/accesstoken/get";
    AccessToken accessToken =
        accessTokenServiceClient.executeRequest(requestPath, "POST", "", AccessToken.class);

    AccessTokenCacheService.put(key, accessToken);
    return accessToken;
  }

  public CompletableFuture<AccessToken> getAccessTokenAsync() {
    String key =
        vippsConfigurationOptions.getClientId() + vippsConfigurationOptions.getClientSecret();

    return CompletableFuture.supplyAsync(() -> AccessTokenCacheService.get(key))
        .thenCompose(
            cachedToken -> {
              if (cachedToken != null) {
                return CompletableFuture.completedFuture(cachedToken);
              }

              String requestPath =
                  UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
                      + "/accesstoken/get";

              CompletableFuture<AccessToken> accessTokenCompletableFuture =
                  accessTokenServiceClient.executeRequestAsync(
                      requestPath, "POST", "", AccessToken.class);

              accessTokenCompletableFuture.thenAccept(
                  (accessToken) -> {
                    AccessTokenCacheService.put(key, accessToken);
                  });

              return accessTokenCompletableFuture;
            });
  }
}
