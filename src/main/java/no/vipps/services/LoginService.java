package no.vipps.services;

import java.util.UUID;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.model.login.AuthenticationMethod;
import no.vipps.model.login.StartLoginUriRequest;

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
}
