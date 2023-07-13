package no.vipps.model.login;

public class StartLoginUriRequest {
  private String scope;

  private String redirectUri;

  private AuthenticationMethod authenticationMethod;

  public StartLoginUriRequest(
      String scope, String redirectUri, AuthenticationMethod authenticationMethod) {
    this.scope = scope;
    this.redirectUri = redirectUri;
    this.authenticationMethod = authenticationMethod;
  }

  public String getScope() {
    return scope;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public AuthenticationMethod getAuthenticationMethod() {
    return authenticationMethod;
  }
}
