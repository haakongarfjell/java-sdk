package no.vipps;

import no.vipps.services.*;

public interface IVippsApi {

  CheckoutService checkoutService();

  EpaymentService epaymentService();

  AccessTokenService accessTokenService();
}
