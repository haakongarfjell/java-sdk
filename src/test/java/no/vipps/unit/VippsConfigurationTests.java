package no.vipps.unit;

import no.vipps.exceptions.VippsUserException;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.services.AccessTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VippsConfigurationTests {
  @Test
  public void usingVippsConfigurationWithoutRunningConfigureThrowsException() throws Exception {
    Assertions.assertThrows(
        VippsUserException.class,
        () -> {
          VippsConfiguration.reset();
          AccessTokenService.getAccessToken().getToken();
        });
  }

  @Test
  public void usingVippsConfigurationWithInvalidThrowsException() {
    Assertions.assertThrows(
        VippsUserException.class,
        () -> VippsConfiguration.getInstance().configureVipps(null, null));
  }
}
