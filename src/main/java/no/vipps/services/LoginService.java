package no.vipps.services;

import java.util.UUID;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.login.AuthenticationMethod;
import no.vipps.model.login.OauthTokenResponse;
import no.vipps.model.login.StartLoginUriRequest;
import no.vipps.model.login.TokenRequest;

public class LoginService {

  public static String GetStartLoginUri(StartLoginUriRequest startLoginUriRequest) {
    String startLoginUri =
        VippsConfiguration.getInstance().getBaseUrl()
            + "/access-management-1.0/access/oauth2/auth"
            + "?client_id="
            + VippsConfiguration.getInstance().getClientId()
            + "&response_type=code"
            + "&scope="
            + startLoginUriRequest.getScope()
            + "&state="
            + UUID.randomUUID().toString()
            + "&redirect_uri="
            + startLoginUriRequest.getRedirectUri();

    if (startLoginUriRequest.getAuthenticationMethod() == AuthenticationMethod.Post) {
      startLoginUri += "&response_mode=form_post";
    }
    return startLoginUri;
  }

  public static OauthTokenResponse getWebLoginToken(
      TokenRequest getTokenRequest, AuthenticationMethod authenticationMethod) {
    getTokenRequest.setGrantType("authorization_code");
    if (authenticationMethod == AuthenticationMethod.Post) {
      getTokenRequest.setClientId(VippsConfiguration.getInstance().getClientId());
      getTokenRequest.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getloginServiceClientPost()
          .executeFormRequest(getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
    }
    return VippsServices.getloginServiceClientBasic()
        .executeFormRequest(getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
  }

  private static String getRequestPath() {
    return VippsConfiguration.getInstance().getBaseUrl() + "/access-management-1.0/access/oauth2/token";
  }
}
