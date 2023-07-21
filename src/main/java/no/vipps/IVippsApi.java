package no.vipps;

import no.vipps.services.*;

public interface IVippsApi {

  LoginService loginService();

  CheckoutService checkoutService();

  EpaymentService epaymentService();

  WebhooksService webhooksService();

  AccessTokenService accessTokenService();
}
