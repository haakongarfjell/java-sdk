package no.vippsdemo.demo;

import no.vipps.VippsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import no.vipps.model.checkout.Amount;
import no.vipps.model.checkout.InitiateSessionRequest;
import no.vipps.model.checkout.InitiateSessionRequestLogistics;
import no.vipps.model.checkout.InitiateSessionRequestMerchantInfo;
import no.vipps.model.checkout.InitiateSessionRequestTransaction;
import no.vipps.model.checkout.InitiateSessionResponse;
import no.vipps.model.checkout.LogisticsOptionBase;
import no.vipps.model.checkout.PostenLogisticsOption;
import no.vipps.model.checkout.PostenLogisticsType;
import no.vipps.model.checkout.PostnordLogisticsOption;
import no.vipps.model.checkout.PostnordLogisticsType;
import no.vipps.model.checkout.SessionResponse;

@Controller
public class CheckoutController {
    @Autowired
    private VippsApi vippsApi;
  @RequestMapping(value = "/checkout", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  @ResponseBody
  public InitiateSessionResponse checkout(@RequestBody StartCheckoutRequest body) {

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

    InitiateSessionResponse sessionResponse = vippsApi.checkoutService().initiateSession(sessionInitiationRequest);
    return sessionResponse;
  }

  @RequestMapping("/ordercomplete")
  public String ordercomplete(@RequestParam("reference") String reference, Model model) {
    SessionResponse sessionInfo = vippsApi.checkoutService().getSessionInfo(reference);
    model.addAttribute("sessionState", sessionInfo.getSessionState().toString());
    return "ordercomplete";
  }

  private List<LogisticsOptionBase> getFixedOptions() {
    PostenLogisticsOption postenOption = PostenLogisticsOption.builder()
        .id("shippingoption-posten-mailbox")
        .amount(Amount.builder().value(1000).currency("NOK").build())
        .type(PostenLogisticsType.MAILBOX)
        .build();
    PostnordLogisticsOption postNordOption = PostnordLogisticsOption.builder()
        .id("shippingoption-postnord-pickup")
        .amount(Amount.builder().value(1000).currency("NOK").build())
        .type(PostnordLogisticsType.PICKUP_POINT)
        .build();
    List<LogisticsOptionBase> options = new ArrayList<>();
    options.add(postenOption);
    options.add(postNordOption);
    return options;
  }

}