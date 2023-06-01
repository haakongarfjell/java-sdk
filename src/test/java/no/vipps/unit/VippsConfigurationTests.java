package no.vipps.unit;

import no.vipps.exceptions.VippsUserException;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.services.AccessTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VippsConfigurationTests {
  @Test
  public void vipps_configuration_without_running_configure_throws_exception() {
    Assertions.assertThrows(
        VippsUserException.class,
        () -> {
          VippsConfiguration.reset();
          AccessTokenService.getAccessToken().getToken();
        });
  }

  @Test
  public void using_vipps_configuration_with_invalid_throws_exception() {
    Assertions.assertThrows(
        VippsUserException.class,
        () -> VippsConfiguration.getInstance().configureVipps(null, null));
  }
}
