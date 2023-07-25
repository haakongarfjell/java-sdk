package no.vipps;

import no.vipps.infrastructure.*;
import no.vipps.services.AccessTokenService;
import no.vipps.services.CheckoutService;
import no.vipps.services.EpaymentService;

public class VippsApi implements IVippsApi {

  private final VippsConfigurationOptions vippsConfigurationOptions;
  private final VippsClient vippsClient;
  private final AccessTokenService accessTokenService;
  private final CheckoutService checkoutService;
  private final EpaymentService epaymentService;

  public VippsApi(VippsConfigurationOptions vippsConfigurationOptions) {
    this.vippsConfigurationOptions = vippsConfigurationOptions;
    vippsClient = new VippsHttpClient(vippsConfigurationOptions);

    accessTokenService =
        new AccessTokenService(
            new AccessTokenServiceClient(vippsClient, vippsConfigurationOptions),
            vippsConfigurationOptions);

    this.checkoutService = new CheckoutService(
            new CheckoutServiceClient(vippsClient, vippsConfigurationOptions),
            vippsConfigurationOptions);

    this.epaymentService = new EpaymentService(
            new EpaymentServiceClient(vippsClient, accessTokenService, vippsConfigurationOptions),
            vippsConfigurationOptions);
  }

  @Override
  public CheckoutService checkoutService() {
    return this.checkoutService;
  }

  @Override
  public EpaymentService epaymentService() {
    return this.epaymentService;
  }

  @Override
  public AccessTokenService accessTokenService() {
    return accessTokenService;
  }
}
