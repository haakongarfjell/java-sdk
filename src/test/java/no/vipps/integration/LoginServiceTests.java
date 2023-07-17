package no.vipps.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.cdimascio.dotenv.Dotenv;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.login.AuthenticationMethod;
import no.vipps.model.login.InitCibaRequest;
import no.vipps.model.login.InitCibaResponse;
import no.vipps.model.login.StartLoginUriRequest;
import no.vipps.services.LoginService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginServiceTests {

  private static final String CUSTOMER_PHONE_NUMBER = "47375750";

  @BeforeAll
  public static void authenticate() {
    Dotenv dotenv = Dotenv.configure().load();
    VippsConfigurationOptions config =
        VippsConfigurationOptions.builder()
            .clientId(dotenv.get("CLIENT_ID"))
            .clientSecret(dotenv.get("CLIENT_SECRET"))
            .subscriptionKey(dotenv.get("OCP_APIM_SUBSCRIPTION_KEY"))
            .merchantSerialNumber(dotenv.get("MSN"))
            .isUseTestMode(true)
            .pluginName("Vipps.net.IntegrationTests")
            .pluginVersion("1.0.0")
            .build();
    VippsConfiguration.getInstance().configureVipps(config, null);
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
        LoginService.InitCiba(initCibaRequest, AuthenticationMethod.Post);
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
        LoginService.GetStartLoginUri(startLoginUriRequest, AuthenticationMethod.Post);
    assertNotNull(redirectUri);
    assertTrue(redirectUri.contains("redirect_uri=http://localhost:3000"));
    assertTrue(redirectUri.contains("response_mode=form_post"));
  }
}
