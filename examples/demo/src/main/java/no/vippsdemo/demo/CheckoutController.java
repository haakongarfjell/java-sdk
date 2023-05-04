package no.vippsdemo.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.ws.rs.Consumes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.checkout.Amount;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionRequestLogistics;
import no.vipps.model.checkout.InitiateSessionRequestMerchantInfo;
import no.vipps.model.checkout.InitiateSessionRequestTransaction;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.LogisticsFixedOptionsInner;
import no.vipps.model.checkout.LogisticsOptionBaseAmount;
import no.vipps.model.checkout.PostenLogisticsOption;
import no.vipps.model.checkout.PostenLogisticsType;
import no.vipps.model.checkout.PostnordLogisticsOption;
import no.vipps.model.checkout.PostnordLogisticsType;
import no.vipps.model.checkout.SessionResponse;
import no.vipps.services.CheckoutService;

@Controller
public class CheckoutController {

  @PostMapping("/checkout")
  @Consumes(MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public InitiateSessionResponse checkout(@RequestBody StartCheckoutRequest body) {
    VippsConfigurationOptions config = VippsConfigurationOptions.builder()
        .clientId(body.clientId)
        .clientSecret(body.clientSecret)
        .subscriptionKey(body.subscriptionKey)
        .merchantSerialNumber(body.merchantSerialNumber)
        .pluginName("Java-Sdk-Demo")
        .pluginVersion("1.0.0")
        .isUseTestMode(true)
        .build();
    VippsConfiguration.getInstance().configureVipps(config, null);

    String reference = UUID.randomUUID().toString();
    InitiateSessionRequest sessionInitiationRequest = InitiateSessionRequest.builder()
        .transaction(
            InitiateSessionRequestTransaction.builder()
                .amount(Amount.builder().currency("NOK").value(10000)
                    .build())
                .reference(reference)
                .paymentDescription("CheckoutSession from Java Demo")
                .build())
        .merchantInfo(
            InitiateSessionRequestMerchantInfo.builder()
                .callbackAuthorizationToken(
                    UUID.randomUUID().toString())
                .callbackUrl("https://no.where.com/callback")
                .returnUrl("http://127.0.0.1:8080/ordercomplete?reference="
                    + reference)
                .termsAndConditionsUrl("https://no.where.com/terms")
                .build())
        .logistics(InitiateSessionRequestLogistics.builder()
            .fixedOptions(getFixedOptions())
            .build())
        .build();

    InitiateSessionResponse sessionResponse = CheckoutService.initiateSession(sessionInitiationRequest);
    return sessionResponse;
  }

  @RequestMapping("/ordercomplete")
  public String ordercomplete(@RequestParam("reference") String reference, Model model) {
    SessionResponse sessionInfo = CheckoutService.getSessionInfo(reference);
    model.addAttribute("sessionState", sessionInfo.getSessionState().toString());
    return "ordercomplete";
  }

  private List<LogisticsFixedOptionsInner> getFixedOptions() {
    PostenLogisticsOption postenOption = PostenLogisticsOption.builder()
        .id("shippingoption-posten-mailbox")
        .amount(LogisticsOptionBaseAmount.builder().value(1000).currency("NOK").build())
        .type(PostenLogisticsType.MAILBOX)
        .build();
    PostnordLogisticsOption postNordOption = PostnordLogisticsOption.builder()
        .id("shippingoption-postnord-pickup")
        .amount(LogisticsOptionBaseAmount.builder().value(1000).currency("NOK").build())
        .type(PostnordLogisticsType.PICKUP_POINT)
        .build();
    List<LogisticsFixedOptionsInner> options = new ArrayList<>();
    options.add(postenOption);
    options.add(postNordOption);
    return options;
  }

}