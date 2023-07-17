package no.vippsdemo.demo;
import no.vipps.VippsApi;
import no.vipps.model.epayment.*;
import no.vipps.model.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Controller
public class LoginController {
    @Autowired
    private VippsApi vippsApi;


    @RequestMapping(value = "/LoginUri", method = RequestMethod.GET)
    @ResponseBody
    public String GetStartLoginUri()
    {
        StartLoginUriRequest startLoginUriRequest = StartLoginUriRequest.builder()
                .redirectUri("http://localhost:3000")
                .scope("openid email name phoneNumber")
                .build();
        return vippsApi.loginService().GetStartLoginUri(startLoginUriRequest, AuthenticationMethod.Post);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<OauthTokenResponse> GetWebLoginToken(@RequestParam("code") String code)
    {
        TokenRequest getTokenRequest = TokenRequest.builder()
                .redirectUri("http://localhost:3000")
                .code(code)
                .build();
        return vippsApi.loginService().getWebLoginTokenAsync(getTokenRequest, AuthenticationMethod.Post);
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
        CreatePaymentResponse response = vippsApi.epaymentService().createPayment(request);
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

        return vippsApi.loginService().InitCibaAsync(initCibaRequest, AuthenticationMethod.Basic);
    }

    @RequestMapping(value = "/ciba-token-no-redirect", method = RequestMethod.POST)
    @ResponseBody
    public OauthTokenResponse GetCibaNoRedirect(@RequestParam("authReqId") String authReqId) {
        return vippsApi.loginService().GetCibaTokenNoRedirect(authReqId, AuthenticationMethod.Basic);
    }

    @RequestMapping(value = "/ciba-token-redirect", method = RequestMethod.POST)
    @ResponseBody
    public OauthTokenResponse GetCibaRedirect(@RequestParam("code") String code) {
        return vippsApi.loginService().GetCibaTokenRedirect(code, AuthenticationMethod.Basic);
    }

}
