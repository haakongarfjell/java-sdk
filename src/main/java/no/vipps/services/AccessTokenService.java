package no.vipps.services;

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
        VippsServices.getAccessTokenServiceClient().executeRequest(requestPath, "POST");

    AccessTokenCacheService.put(key, accessToken);
    return accessToken;
  }
}
