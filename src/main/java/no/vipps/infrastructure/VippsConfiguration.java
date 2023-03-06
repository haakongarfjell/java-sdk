package no.vipps.infrastructure;

import java.util.Objects;

public class VippsConfiguration {

  private static VippsConfiguration INSTANCE;
  private String systemName;
  private String systemVersion;
  private String pluginName = "checkout-sandbox";
  private String pluginVersion = "1.0";
  private String clientId;
  private String clientSecret;
  private String subscriptionKey;
  private String merchantSerialNumber;
  private Boolean testMode;
  private VippsHttpClient vippsHttpClient;

  public static VippsConfiguration getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new VippsConfiguration();
    }

    return INSTANCE;
  }

  public void configureVipps(
      VippsConfigurationOptions vippsConfigurationOptions,
      VippsHttpClient vippsHttpClient
  ) {
    Objects.requireNonNull(vippsConfigurationOptions, "vippsConfigurationOptions must not be null");

    if (vippsHttpClient != null) {
      setVippsHttpClient(vippsHttpClient);
    }

    pluginName = vippsConfigurationOptions.getPluginName();
    pluginVersion = vippsConfigurationOptions.getPluginVersion();
    clientId = vippsConfigurationOptions.getClientId();
    clientSecret = vippsConfigurationOptions.getClientSecret();
    subscriptionKey = vippsConfigurationOptions.getSubscriptionKey();
    merchantSerialNumber = vippsConfigurationOptions.getMerchantSerialNumber();
    testMode = vippsConfigurationOptions.isUseTestMode();
  }

  private RuntimeException createMissingConfigException(String propertyName) {
    return new IllegalArgumentException("VippsConfiguration incomplete - " + propertyName +
        " is missing. Have you run configureVipps?");
  }

  public String getPluginName() {
    return pluginName;
  }

  public String getPluginVersion() {
    return pluginVersion;
  }

  public String getClientId() {
    if (clientId == null) {
      throw createMissingConfigException("clientId");
    }
    return clientId;
  }

  public String getClientSecret() {
    if (clientSecret == null) {
      throw createMissingConfigException("clientSecret");
    }
    return clientSecret;
  }

  public String getSubscriptionKey() {
    if (subscriptionKey == null) {
      throw createMissingConfigException("subscriptionKey");
    }
    return subscriptionKey;
  }

  public String getMerchantSerialNumber() {
    if (merchantSerialNumber == null) {
      throw createMissingConfigException("merchantSerialNumber");
    }
    return merchantSerialNumber;
  }

  public boolean isTestMode() {
    if (testMode == null) {
      throw createMissingConfigException("testMode");
    }
    return testMode;
  }

  public String getBaseUrl() {
    return isTestMode() ? "https://api-test.vipps.no" : "https://api.vipps.no";
  }

  public VippsHttpClient getVippsHttpClient() {
    if (vippsHttpClient == null) {
      vippsHttpClient = createDefaultVippsHttpClient();
    }
    return vippsHttpClient;
  }

  public void setVippsHttpClient(VippsHttpClient httpClient) {
    vippsHttpClient = httpClient;
  }

  private VippsHttpClient createDefaultVippsHttpClient() {
    return new VippsHttpClient();
  }

  public String getSystemName() {
    return systemName;
  }

  public String getSystemVersion() {
    return systemVersion;
  }
}
