package no.vipps.helpers;

public class UrlHelper {

  public static String getBaseUrl(boolean isTestMode) {
    return isTestMode ? "https://apitest.vipps.no" : "https://api.vipps.no";
  }
}
