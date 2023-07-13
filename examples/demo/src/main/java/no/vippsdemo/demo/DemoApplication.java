package no.vippsdemo.demo;

import io.github.cdimascio.dotenv.Dotenv;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsConfigurationOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {

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
    VippsConfiguration.getInstance().configureVipps(config, null);

    SpringApplication.run(DemoApplication.class, args);
  }
}
