package no.vipps;

import no.vipps.infrastructure.*;
import no.vipps.services.AccessTokenService;
import no.vipps.services.CheckoutService;
import no.vipps.services.EpaymentService;

public class VippsApi implements IVippsApi {

  private final VippsConfigurationOptions vippsConfigurationOptions;
  private final VippsClient vippsClient;

  private final AccessTokenService accessTokenService;

  private VippsApi(VippsConfigurationOptions vippsConfigurationOptions) {
    this.vippsConfigurationOptions = vippsConfigurationOptions;
    vippsClient = new VippsHttpClient(vippsConfigurationOptions);
    accessTokenService =
        new AccessTokenService(
            new AccessTokenServiceClient(vippsClient, vippsConfigurationOptions),
            vippsConfigurationOptions);
  }

  public static VippsApi Create(VippsConfigurationOptions vippsConfigurationOptions) {
    return new VippsApi(vippsConfigurationOptions);
  }

  @Override
  public CheckoutService checkoutService() {
    return new CheckoutService(
        new CheckoutServiceClient(vippsClient, vippsConfigurationOptions),
        vippsConfigurationOptions);
  }

  @Override
  public EpaymentService epaymentService() {
    return new EpaymentService(
        new EpaymentServiceClient(vippsClient, accessTokenService, vippsConfigurationOptions),
        vippsConfigurationOptions);
  }

  @Override
  public AccessTokenService accessTokenService() {
    return accessTokenService;
  }
}
