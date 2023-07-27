package no.vippsdemo.demo;

import no.vipps.IVippsApi;
import no.vipps.VippsApi;
import no.vipps.infrastructure.VippsConfigurationOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public VippsApi vippsApi() {
    VippsConfigurationOptions config = VippsConfigurationOptions.builder()
            .clientId(System.getenv("CLIENT_ID"))
            .clientSecret(System.getenv("CLIENT_SECRET"))
            .subscriptionKey(System.getenv("OCP_APIM_SUBSCRIPTION_KEY"))
            .merchantSerialNumber(System.getenv("MSN"))
            .pluginName("Java-Sdk-Demo")
            .pluginVersion("1.0.0")
            .isUseTestMode(true)
            .build();
    return new VippsApi(config);
  }
}
