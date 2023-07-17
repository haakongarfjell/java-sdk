package no.vipps.services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.UrlHelper;
import no.vipps.infrastructure.LoginServiceClientBasic;
import no.vipps.infrastructure.LoginServiceClientPost;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.login.*;

public class LoginService {

  private final LoginServiceClientBasic loginServiceClientBasic;
  private final LoginServiceClientPost loginServiceClientPost;

  private final VippsConfigurationOptions vippsConfigurationOptions;

  public LoginService(
      LoginServiceClientBasic loginServiceClientBasic,
      LoginServiceClientPost loginServiceClientPost,
      VippsConfigurationOptions vippsConfigurationOptions) {
    this.loginServiceClientBasic = loginServiceClientBasic;
    this.loginServiceClientPost = loginServiceClientPost;
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  public String GetStartLoginUri(
      StartLoginUriRequest startLoginUriRequest, AuthenticationMethod authenticationMethod) {
    String startLoginUri =
        UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
            + "/access-management-1.0/access/oauth2/auth"
            + "?client_id="
            + vippsConfigurationOptions.getClientId()
            + "&response_type=code"
            + "&scope="
            + startLoginUriRequest.getScope()
            + "&state="
            + UUID.randomUUID().toString()
            + "&redirect_uri="
            + startLoginUriRequest.getRedirectUri();

    if (authenticationMethod == AuthenticationMethod.Post) {
      startLoginUri += "&response_mode=form_post";
    }
    return startLoginUri;
  }

  public OauthTokenResponse getWebLoginToken(
      TokenRequest getTokenRequest, AuthenticationMethod authenticationMethod) {
    getTokenRequest.setGrantType("authorization_code");
    if (authenticationMethod == AuthenticationMethod.Post) {
      getTokenRequest.setClientId(vippsConfigurationOptions.getClientId());
      getTokenRequest.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequest(
          getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
    }
    return loginServiceClientBasic.executeFormRequest(
        getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
  }

  public CompletableFuture<OauthTokenResponse> getWebLoginTokenAsync(
      TokenRequest getTokenRequest, AuthenticationMethod authenticationMethod) {
    getTokenRequest.setGrantType("authorization_code");
    if (authenticationMethod == AuthenticationMethod.Post) {
      getTokenRequest.setClientId(vippsConfigurationOptions.getClientId());
      getTokenRequest.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequestAsync(
          getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
    }
    return loginServiceClientBasic.executeFormRequestAsync(
        getRequestPath(), "POST", getTokenRequest, OauthTokenResponse.class);
  }

  public InitCibaResponse InitCiba(
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
      initCibaBody.setClientId(vippsConfigurationOptions.getClientId());
      initCibaBody.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequest(
          getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
    }
    return loginServiceClientBasic.executeFormRequest(
        getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
  }

  public CompletableFuture<InitCibaResponse> InitCibaAsync(
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
      initCibaBody.setClientId(vippsConfigurationOptions.getClientId());
      initCibaBody.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequestAsync(
          getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
    }
    return loginServiceClientBasic.executeFormRequestAsync(
        getCibaRequestPath(), "POST", initCibaBody, InitCibaResponse.class);
  }

  public OauthTokenResponse GetCibaTokenNoRedirect(
      String authReqId, AuthenticationMethod authenticationMethod) {
    CibaTokenNoRedirectRequest cibaTokenRequest =
        CibaTokenNoRedirectRequest.builder()
            .authReqId(authReqId)
            .grantType("urn:openid:params:grant-type:ciba")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(vippsConfigurationOptions.getClientId());
      cibaTokenRequest.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequest(
          getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return loginServiceClientBasic.executeFormRequest(
        getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  public CompletableFuture<OauthTokenResponse> GetCibaTokenNoRedirectAsync(
      String authReqId, AuthenticationMethod authenticationMethod) {
    CibaTokenNoRedirectRequest cibaTokenRequest =
        CibaTokenNoRedirectRequest.builder()
            .authReqId(authReqId)
            .grantType("urn:openid:params:grant-type:ciba")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(vippsConfigurationOptions.getClientId());
      cibaTokenRequest.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequestAsync(
          getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return loginServiceClientBasic.executeFormRequestAsync(
        getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  public OauthTokenResponse GetCibaTokenRedirect(
      String code, AuthenticationMethod authenticationMethod) {
    CibaTokenRedirectRequest cibaTokenRequest =
        CibaTokenRedirectRequest.builder()
            .code(code)
            .grantType("urn:vipps:params:grant-type:ciba-redirect")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(vippsConfigurationOptions.getClientId());
      cibaTokenRequest.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequest(
          getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return loginServiceClientBasic.executeFormRequest(
        getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  public CompletableFuture<OauthTokenResponse> GetCibaTokenRedirectAsync(
      String code, AuthenticationMethod authenticationMethod) {
    CibaTokenRedirectRequest cibaTokenRequest =
        CibaTokenRedirectRequest.builder()
            .code(code)
            .grantType("urn:vipps:params:grant-type:ciba-redirect")
            .build();
    if (authenticationMethod == AuthenticationMethod.Post) {
      cibaTokenRequest.setClientId(vippsConfigurationOptions.getClientId());
      cibaTokenRequest.setClientSecret(vippsConfigurationOptions.getClientSecret());
      return loginServiceClientPost.executeFormRequestAsync(
          getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
    }
    return loginServiceClientBasic.executeFormRequestAsync(
        getRequestPath(), "POST", cibaTokenRequest, OauthTokenResponse.class);
  }

  private String getRequestPath() {
    return UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
        + "/access-management-1.0/access/oauth2/token";
  }

  private String getCibaRequestPath() {
    return UrlHelper.getBaseUrl(vippsConfigurationOptions.getIsUseTestMode())
        + "/vipps-login-ciba/api/backchannel/authentication";
  }
}
