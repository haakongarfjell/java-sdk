package no.vipps.services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsServices;
import no.vipps.model.login.*;

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
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequest(getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequest(getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
  }

  public static CompletableFuture<OauthTokenResponse> getWebLoginTokenAsync(
      TokenRequest getTokenRequest, AuthenticationMethod authenticationMethod) {
    getTokenRequest.setGrantType("authorization_code");
    if (authenticationMethod == AuthenticationMethod.Post) {
      getTokenRequest.setClientId(VippsConfiguration.getInstance().getClientId());
      getTokenRequest.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequestAsync(
              getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequestAsync(
            getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
  }

  public static InitCibaResponse InitCiba(
      InitCibaRequest initCibaRequest, AuthenticationMethod authenticationMethod) {
    InitCibaBody initCibaBody =
        InitCibaBody.builder()
            .scope(initCibaRequest.getScope())
            .loginHint("urn:mobilenumber:" + initCibaRequest.getPhoneNumber())
            .state(UUID.randomUUID().toString())
            .bindingMessage(initCibaRequest.getBindingMessage().toUpperCase())
            .build();

    if (initCibaRequest.getRedirectUri() != null) {
      initCibaBody.setRedirectUri(initCibaRequest.getRedirectUri());
      initCibaBody.setRequestedFlow("login_to_webpage");
    }

    if (authenticationMethod == AuthenticationMethod.Post) {
      initCibaBody.setClientId(VippsConfiguration.getInstance().getClientId());
      initCibaBody.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequest(getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequest(getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
  }

  public static CompletableFuture<InitCibaResponse> InitCibaAsync(
      InitCibaRequest initCibaRequest, AuthenticationMethod authenticationMethod) {
    InitCibaBody initCibaBody =
        InitCibaBody.builder()
            .scope(initCibaRequest.getScope())
            .loginHint("urn:mobilenumber:" + initCibaRequest.getPhoneNumber())
            .state(UUID.randomUUID().toString())
            .bindingMessage(initCibaRequest.getBindingMessage().toUpperCase())
            .build();

    if (initCibaRequest.getRedirectUri() != null) {
      initCibaBody.setRedirectUri(initCibaRequest.getRedirectUri());
      initCibaBody.setRequestedFlow("login_to_webpage");
    }

    if (authenticationMethod == AuthenticationMethod.Post) {
      initCibaBody.setClientId(VippsConfiguration.getInstance().getClientId());
      initCibaBody.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequestAsync(
              getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequestAsync(
            getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
  }

  public static OauthTokenResponse GetCibaTokenNoRedirect(
      String authReqId, AuthenticationMethod authenticationMethod) {
    CibaTokenNoRedirectRequest cibaTokenRequest =
        CibaTokenNoRedirectRequest.builder()
            .authReqId(authReqId)
            .grantType("urn:openid:params:grant-type:ciba")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(VippsConfiguration.getInstance().getClientId());
      cibaTokenRequest.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequest(getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequest(getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  public static CompletableFuture<OauthTokenResponse> GetCibaTokenNoRedirectAsync(
      String authReqId, AuthenticationMethod authenticationMethod) {
    CibaTokenNoRedirectRequest cibaTokenRequest =
        CibaTokenNoRedirectRequest.builder()
            .authReqId(authReqId)
            .grantType("urn:openid:params:grant-type:ciba")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(VippsConfiguration.getInstance().getClientId());
      cibaTokenRequest.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequestAsync(
              getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequestAsync(
            getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  public static OauthTokenResponse GetCibaTokenRedirect(
      String code, AuthenticationMethod authenticationMethod) {
    CibaTokenRedirectRequest cibaTokenRequest =
        CibaTokenRedirectRequest.builder()
            .code(code)
            .grantType("urn:vipps:params:grant-type:ciba-redirect")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(VippsConfiguration.getInstance().getClientId());
      cibaTokenRequest.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequest(getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequest(getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  public static CompletableFuture<OauthTokenResponse> GetCibaTokenRedirectAsync(
      String code, AuthenticationMethod authenticationMethod) {
    CibaTokenRedirectRequest cibaTokenRequest =
        CibaTokenRedirectRequest.builder()
            .code(code)
            .grantType("urn:vipps:params:grant-type:ciba-redirect")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(VippsConfiguration.getInstance().getClientId());
      cibaTokenRequest.setClientSecret(VippsConfiguration.getInstance().getClientSecret());
      return VippsServices.getLoginServiceClientPost()
          .executeFormRequestAsync(
              getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return VippsServices.getLoginServiceClientBasic()
        .executeFormRequestAsync(
            getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  private static String getRequestPath() {
    return VippsConfiguration.getInstance().getBaseUrl()
        + "/access-management-1.0/access/oauth2/token";
  }

  private static String getCibaRequestPath() {
    return VippsConfiguration.getInstance().getBaseUrl()
        + "/vipps-login-ciba/api/backchannel/authentication";
  }
}
