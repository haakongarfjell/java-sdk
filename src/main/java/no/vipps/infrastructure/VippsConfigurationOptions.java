package no.vipps.infrastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VippsConfigurationOptions {
  private final String systemName = "Vipps Java SDK";
  private final String systemVersion = "1.0";
  private String pluginName;
  private String pluginVersion;
  private String clientId;
  private String clientSecret;
  private String subscriptionKey;
  private String merchantSerialNumber;
  boolean isUseTestMode;
}
