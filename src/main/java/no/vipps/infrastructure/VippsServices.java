package no.vipps.infrastructure;

public final class VippsServices {
  private static EpaymentServiceClient epaymentServiceClient;
  private static AccessTokenServiceClient accessTokenServiceClient;
  private static CheckoutServiceClient _checkoutServiceClient;

  private static LoginServiceClientPost loginServiceClientPost;

  private static LoginServiceClientBasic loginServiceClientBasic;

  public static EpaymentServiceClient getEpaymentServiceClient() {
    if (epaymentServiceClient == null) {
      System.out.println("Halla kompis");
      VippsHttpClient client = VippsConfiguration.getInstance().getVippsHttpClient();
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

  public static LoginServiceClientPost getloginServiceClientPost() {
    if (loginServiceClientPost == null) {
      loginServiceClientPost =
          new LoginServiceClientPost(VippsConfiguration.getInstance().getVippsHttpClient());
    }
    return loginServiceClientPost;
  }

  public static LoginServiceClientBasic getloginServiceClientBasic() {
    if (loginServiceClientBasic == null) {
      loginServiceClientBasic =
          new LoginServiceClientBasic(VippsConfiguration.getInstance().getVippsHttpClient());
    }
    return loginServiceClientBasic;
  }
}
