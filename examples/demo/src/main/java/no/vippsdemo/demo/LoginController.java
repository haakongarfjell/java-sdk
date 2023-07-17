package no.vippsdemo.demo;

import io.github.cdimascio.dotenv.Dotenv;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.checkout.UserFlow;
import no.vipps.model.epayment.*;
import no.vipps.model.login.*;
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
        StartLoginUriRequest startLoginUriRequest = StartLoginUriRequest.builder()
                .authenticationMethod(AuthenticationMethod.Basic)
                .redirectUri("http://localhost:3000")
                .scope("openid email name phoneNumber")
                .build();
        return LoginService.GetStartLoginUri(startLoginUriRequest);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<OauthTokenResponse> GetWebLoginToken(@RequestParam("code") String code)
    {
        TokenRequest getTokenRequest = TokenRequest.builder()
                .redirectUri("http://localhost:3000")
                .code(code)
                .build();
        return LoginService.getWebLoginTokenAsync(getTokenRequest, AuthenticationMethod.Basic);
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

    @RequestMapping(value = "/init-ciba", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CompletableFuture<InitCibaResponse> InitCiba() {
        InitCibaRequest initCibaRequest = InitCibaRequest.builder()
                .scope("openid email name phoneNumber")
                .phoneNumber("47375750")
                .bindingMessage("XYZ-123")
                .redirectUri("http://localhost:3000")
                .build();

        return LoginService.InitCibaAsync(initCibaRequest, AuthenticationMethod.Basic);
    }

    @RequestMapping(value = "/ciba-token-no-redirect", method = RequestMethod.POST)
    @ResponseBody
    public OauthTokenResponse GetCibaNoRedirect(@RequestParam("authReqId") String authReqId) {
        return LoginService.GetCibaTokenNoRedirect(authReqId, AuthenticationMethod.Basic);
    }

    @RequestMapping(value = "/ciba-token-redirect", method = RequestMethod.POST)
    @ResponseBody
    public OauthTokenResponse GetCibaRedirect(@RequestParam("code") String code) {
        return LoginService.GetCibaTokenRedirect(code, AuthenticationMethod.Basic);
    }

}
