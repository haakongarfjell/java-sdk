package no.vipps.integration;

import no.vipps.VippsApi;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.login.AuthenticationMethod;
import no.vipps.model.login.InitCibaRequest;
import no.vipps.model.login.InitCibaResponse;
import no.vipps.model.login.StartLoginUriRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginServiceTests {

  private static final String CUSTOMER_PHONE_NUMBER = "47375750";
  private static VippsApi vippsApi;

  @BeforeAll
  public static void authenticate() {
    VippsConfigurationOptions config =
        VippsConfigurationOptions.builder()
              .clientId(System.getenv("CLIENT_ID"))
              .clientSecret(System.getenv("CLIENT_SECRET"))
              .subscriptionKey(System.getenv("SUBSCRIPTION_KEY"))
              .merchantSerialNumber(System.getenv("MERCHANT_SERIAL_NUMBER"))
              .isUseTestMode(true)
              .pluginName("Vipps.net.IntegrationTests")
              .pluginVersion("1.0.0")
            .build();
    vippsApi = VippsApi.Create(config);
  }

  @Test
  public void init_ciba_with_redirect() {
    InitCibaRequest initCibaRequest =
        InitCibaRequest.builder()
            .scope("openid email")
            .phoneNumber(CUSTOMER_PHONE_NUMBER)
            .bindingMessage("testing")
            .redirectUri("http://localhost:3000")
            .build();
    InitCibaResponse initCibaResponse =
        vippsApi.loginService().InitCiba(initCibaRequest, AuthenticationMethod.Post);
    assertNotNull(initCibaResponse.getAuthReqId());
    assertNotNull(initCibaResponse.getInterval());
    assertNotNull(initCibaResponse.getExpiresIn());
  }

  @Test
  public void get_start_login_uri() {
    StartLoginUriRequest startLoginUriRequest =
        StartLoginUriRequest.builder()
            .scope("openid email")
            .redirectUri("http://localhost:3000")
            .build();

    String redirectUri =
        vippsApi.loginService().GetStartLoginUri(startLoginUriRequest, AuthenticationMethod.Post);
    assertNotNull(redirectUri);
    assertTrue(redirectUri.contains("redirect_uri=http://localhost:3000"));
    assertTrue(redirectUri.contains("response_mode=form_post"));
  }
}
