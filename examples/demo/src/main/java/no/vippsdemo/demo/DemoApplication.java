package no.vippsdemo.demo;

import io.github.cdimascio.dotenv.Dotenv;
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
    Dotenv dotenv = Dotenv.configure().load();
    VippsConfigurationOptions config = VippsConfigurationOptions.builder()
            .clientId(dotenv.get("CLIENT_ID"))
            .clientSecret(dotenv.get("CLIENT_SECRET"))
            .subscriptionKey(dotenv.get("OCP_APIM_SUBSCRIPTION_KEY"))
            .merchantSerialNumber(dotenv.get("MSN"))
            .pluginName("Java-Sdk-Demo")
            .pluginVersion("1.0.0")
            .isUseTestMode(true)
            .build();
    return new VippsApi(config);
  }
}
