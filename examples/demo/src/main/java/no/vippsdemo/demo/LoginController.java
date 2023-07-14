package no.vippsdemo.demo;

import io.github.cdimascio.dotenv.Dotenv;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.checkout.UserFlow;
import no.vipps.model.epayment.*;
import no.vipps.model.login.AuthenticationMethod;
import no.vipps.model.login.OauthTokenResponse;
import no.vipps.model.login.StartLoginUriRequest;
import no.vipps.model.login.TokenRequest;
import no.vipps.services.EpaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import no.vipps.services.LoginService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Controller
public class LoginController {

    @RequestMapping(value = "/LoginUri", method = RequestMethod.GET)
    @ResponseBody
    public String GetStartLoginUri()
    {
        StartLoginUriRequest startLoginUriRequest = new StartLoginUriRequest(
                "openid email name phoneNumber",
                "http://localhost:3000",
                AuthenticationMethod.Basic
        );
        return LoginService.GetStartLoginUri(startLoginUriRequest);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public OauthTokenResponse GetWebLoginToken(@RequestParam("code") String code)
    {
        TokenRequest getTokenRequest = TokenRequest.builder()
                .redirectUri("http://localhost:3000")
                .code(code)
                .build();
        return LoginService.getWebLoginToken(getTokenRequest, AuthenticationMethod.Basic);
    }

    @RequestMapping(value = "/createPayment", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CreatePaymentResponse CreatePayment()
    {
        CreatePaymentRequest request = CreatePaymentRequest.builder()
                .amount(Amount.builder()
                        .currency(Currency.NOK)
                        .value(1000l)
                        .build())
                .paymentMethod(PaymentMethod.builder()
                        .type(PaymentMethodType.WALLET)
                        .build())
                .customer(Customer.builder()
                        .phoneNumber("4747375750")
                        .build())
                .reference(UUID.randomUUID().toString())
                .userFlow(CreatePaymentRequestUserFlow.WEB_REDIRECT)
                .returnUrl("4747375750")
                .paymentDescription("paymentDescription")
                .profile(ProfileRequest.builder()
                        .scope("name phoneNumber address birthDate email")
                        .build())
                .build();
        CreatePaymentResponse response = EpaymentService.createPayment(request);
        return response;
    }

}
