package no.vipps.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import no.vipps.helpers.Constants;
import okhttp3.Headers;

public class LoginServiceClientBasic extends BaseServiceClient {

  private final VippsConfigurationOptions vippsConfigurationOptions;

  public LoginServiceClientBasic(
      VippsClient vippsClient, VippsConfigurationOptions vippsConfigurationOptions) {
    super(vippsClient);
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  @Override
  Headers getHeaders() {
    return Headers.of(
        new HashMap<String, String>() {
          {
            put(
                Constants.HEADER_NAME_AUTHORIZATION,
                "Basic "
                    + encodeCredentials(
                        vippsConfigurationOptions.getClientId(),
                        vippsConfigurationOptions.getClientSecret()));
          }
        });
  }

  @Override
  CompletableFuture<Headers> getHeadersAsync() {
    return CompletableFuture.completedFuture(this.getHeaders());
  }

  private static String encodeCredentials(String clientId, String clientSecret) {
    String credentials = clientId + ":" + clientSecret;
    byte[] credentialsBytes = credentials.getBytes(StandardCharsets.UTF_8);
    String clientAuthorization = Base64.getEncoder().encodeToString(credentialsBytes);

    return clientAuthorization;
  }
}
