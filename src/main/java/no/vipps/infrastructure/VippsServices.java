package no.vipps.infrastructure;

public final class VippsServices {
  private static EpaymentServiceClient epaymentServiceClient;
  private static AccessTokenServiceClient accessTokenServiceClient;
  private static CheckoutServiceClient _checkoutServiceClient;

  public static EpaymentServiceClient getEpaymentServiceClient() {
    if (epaymentServiceClient == null) {
      epaymentServiceClient =
          new EpaymentServiceClient(VippsConfiguration.getInstance().getVippsHttpClient());
    }
    return epaymentServiceClient;
  }

  public static AccessTokenServiceClient getAccessTokenServiceClient() {
    if (accessTokenServiceClient == null) {
      accessTokenServiceClient =
          new AccessTokenServiceClient(VippsConfiguration.getInstance().getVippsHttpClient());
    }
    return accessTokenServiceClient;
  }

  public static CheckoutServiceClient getCheckoutServiceClient() {
    if (_checkoutServiceClient == null) {
      _checkoutServiceClient =
          new CheckoutServiceClient(VippsConfiguration.getInstance().getVippsHttpClient());
    }
    return _checkoutServiceClient;
  }
}
