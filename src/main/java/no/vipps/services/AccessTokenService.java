package no.vipps.services;

import java.util.concurrent.CompletableFuture;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.accesstoken.AccessToken;

public class AccessTokenService {

  public static AccessToken getAccessToken() {
    String key =
        VippsConfiguration.getInstance().getClientId()
            + VippsConfiguration.getInstance().getClientSecret();
    AccessToken cachedToken = AccessTokenCacheService.get(key);

    if (cachedToken != null) {
      return cachedToken;
    }

    String requestPath = VippsConfiguration.getInstance().getBaseUrl() + "/accesstoken/get";
    AccessToken accessToken =
        VippsServices.getAccessTokenServiceClient()
            .executeRequest(requestPath, "POST", "", AccessToken.class);

    AccessTokenCacheService.put(key, accessToken);
    return accessToken;
  }

  public static CompletableFuture<AccessToken> getAccessTokenAsync() {
    String key =
        VippsConfiguration.getInstance().getClientId()
            + VippsConfiguration.getInstance().getClientSecret();

    return CompletableFuture.supplyAsync(() -> AccessTokenCacheService.get(key))
        .thenCompose(
            cachedToken -> {
              if (cachedToken != null) {
                return CompletableFuture.completedFuture(cachedToken);
              }

              String requestPath =
                  VippsConfiguration.getInstance().getBaseUrl() + "/accesstoken/get";

              CompletableFuture<AccessToken> accessTokenCompletableFuture =
                  VippsServices.getAccessTokenServiceClient()
                      .executeRequestAsync(requestPath, "POST", "", AccessToken.class);

              accessTokenCompletableFuture.thenAccept(
                  (accessToken) -> {
                    AccessTokenCacheService.put(key, accessToken);
                  });

              return accessTokenCompletableFuture;
            });
  }
}
